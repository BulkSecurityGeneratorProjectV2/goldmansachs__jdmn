/*
 * Copyright 2016 Goldman Sachs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.gs.dmn.feel.interpreter;

import com.gs.dmn.context.DMNContext;
import com.gs.dmn.context.environment.Declaration;
import com.gs.dmn.el.analysis.semantics.type.Type;
import com.gs.dmn.feel.analysis.semantics.type.*;
import com.gs.dmn.feel.analysis.syntax.ast.expression.function.Conversion;
import com.gs.dmn.feel.analysis.syntax.ast.expression.function.ConversionKind;
import com.gs.dmn.feel.analysis.syntax.ast.expression.function.FunctionDefinition;
import com.gs.dmn.feel.lib.FEELLib;
import com.gs.dmn.runtime.Context;
import com.gs.dmn.runtime.DMNRuntimeException;
import com.gs.dmn.runtime.Range;
import com.gs.dmn.runtime.function.BuiltinFunction;
import com.gs.dmn.runtime.function.DMNFunction;
import com.gs.dmn.runtime.function.DMNInvocable;
import com.gs.dmn.runtime.function.FEELFunction;
import com.gs.dmn.runtime.interpreter.Result;

import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.*;
import java.time.temporal.TemporalAmount;
import java.util.List;

import static com.gs.dmn.el.analysis.semantics.type.AnyType.ANY;
import static com.gs.dmn.feel.analysis.semantics.type.BooleanType.BOOLEAN;
import static com.gs.dmn.feel.analysis.semantics.type.ComparableDataType.COMPARABLE;
import static com.gs.dmn.feel.analysis.semantics.type.DateType.DATE;
import static com.gs.dmn.feel.analysis.semantics.type.NumberType.NUMBER;
import static com.gs.dmn.feel.analysis.semantics.type.StringType.STRING;
import static com.gs.dmn.feel.analysis.semantics.type.TimeType.TIME;
import static com.gs.dmn.feel.analysis.syntax.ast.expression.function.ConversionKind.*;

public class TypeConverter {
    /*
        A value conforms to a type when the value is in the semantic domain of type (in varies from one dialect to another)
    */
    public static boolean conformsTo(Object value, Type type) {
        if (type == ANY) {
            return true;
        } else if (type == NUMBER && isNumber(value)) {
            return true;
        } else if (type == STRING && isString(value)) {
            return true;
        } else if (type == BOOLEAN && value instanceof Boolean) {
            return true;
        } else if (type == DATE && isDate(value)) {
            return true;
        } else if (type == TIME && isTime(value)) {
            return true;
        } else if (type instanceof DateTimeType && isDateTime(value)) {
            return true;
        } else if (type instanceof DurationType && isDuration(value)) {
            return true;
        } else if (type == COMPARABLE && isComparable(value)) {
            return true;
        } else if ((type instanceof ContextType || type instanceof ItemDefinitionType) && value instanceof Context) {
            Context context = (Context) value;
            CompositeDataType contextType = (CompositeDataType) type;
            for (String member : contextType.getMembers()) {
                if (!conformsTo(context.get(member), contextType.getMemberType(member))) {
                    return false;
                }
            }
            return true;
        } else if (type instanceof RangeType && value instanceof Range) {
            return true;
        } else if (type instanceof ListType && value instanceof List) {
            for (Object obj : (List) value) {
                if (!conformsTo(obj, ((ListType) type).getElementType())) {
                    return false;
                }
            }
            return true;
        } else if (value instanceof FEELFunction && type instanceof FunctionType) {
            FunctionDefinition<Type, DMNContext> functionDefinition = ((FEELFunction) value).getFunctionDefinition();
            return com.gs.dmn.el.analysis.semantics.type.Type.conformsTo(functionDefinition.getType(), type);
        } else if (value instanceof DMNInvocable && type instanceof FunctionType) {
            Type valueType = ((DMNInvocable) value).getType();
            return com.gs.dmn.el.analysis.semantics.type.Type.conformsTo(valueType, type);
        } else if (value instanceof DMNFunction && type instanceof FunctionType) {
            Type valueType = ((DMNFunction) value).getType();
            return com.gs.dmn.el.analysis.semantics.type.Type.conformsTo(valueType, type);
        } else if (value instanceof BuiltinFunction && type instanceof FunctionType) {
            // At least one conforms
            List<Declaration> declarations = ((BuiltinFunction) value).getDeclarations();
            for (Declaration d: declarations) {
                if (com.gs.dmn.el.analysis.semantics.type.Type.conformsTo(d.getType(), type)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private static boolean isString(Object value) {
        return value instanceof String;
    }

    private static boolean isNumber(Object value) {
        return value instanceof Number;
    }

    private static boolean isDuration(Object value) {
        return value instanceof Duration || value instanceof TemporalAmount;
    }

    private static boolean isDateTime(Object value) {
        return value instanceof XMLGregorianCalendar || value instanceof LocalDateTime || value instanceof OffsetDateTime || value instanceof ZonedDateTime;
    }

    private static boolean isTime(Object value) {
        return value instanceof XMLGregorianCalendar || value instanceof OffsetTime || value instanceof LocalTime;
    }

    private static boolean isDate(Object value) {
        return value instanceof XMLGregorianCalendar || value instanceof LocalDate;
    }

    private static boolean isComparable(Object value) {
        return isNumber(value) || isString(value) ||
                isDate(value) || isTime(value) || isDateTime(value) || isDuration(value);
    }

    public Result convertResult(Result result, Type expectedType, FEELLib<?, ?, ?, ?, ?> lib) {
        Object value = Result.value(result);
        Type actualType = Result.type(result);
        if (expectedType == null) {
            expectedType = ANY;
        }

        if (com.gs.dmn.el.analysis.semantics.type.Type.conformsTo(actualType, expectedType)) {
            return Result.of(value, expectedType);
        } else {
            // Dynamic conversion
            return convertValue(value, expectedType, lib);
        }
    }

    public Result convertValue(Object value, Type expectedType, FEELLib<?, ?, ?, ?, ?> lib) {
        // Dynamic conversion
        if (expectedType == null) {
            expectedType = ANY;
        }
        ConversionKind conversionKind = conversionKind(value, expectedType, lib);
        Object newValue = convertValue(value, conversionKind, lib);
        return Result.of(newValue, expectedType);
    }

    public Object convertValue(Object value, Conversion<Type> conversion, FEELLib<?, ?, ?, ?, ?> lib) {
        ConversionKind kind = conversion.getKind();
        return convertValue(value, kind, lib);
    }

    private ConversionKind conversionKind(Object value, Type expectedType, FEELLib<?, ?, ?, ?, ?> lib) {
        if (conformsTo(value, expectedType)) {
            return ConversionKind.NONE;
        } else if (isSingletonList(value) && conformsTo(((List) value).get(0), expectedType)) {
            return SINGLETON_LIST_TO_ELEMENT;
        } else if (expectedType instanceof ListType && conformsTo(value, ((ListType) expectedType).getElementType())) {
            return ELEMENT_TO_SINGLETON_LIST;
        } else if (lib.isDate(value) && expectedType instanceof DateTimeType) {
            return DATE_TO_UTC_MIDNIGHT;
        } else {
            return ConversionKind.CONFORMS_TO;
        }
    }

    private Object convertValue(Object value, ConversionKind kind, FEELLib<?, ?, ?, ?, ?> lib) {
        if (kind == ConversionKind.NONE) {
            return value;
        } else if (kind == ConversionKind.ELEMENT_TO_SINGLETON_LIST) {
            return lib.asList(value);
        } else if (kind == ConversionKind.SINGLETON_LIST_TO_ELEMENT) {
            return lib.asElement((List) value);
        } else if (kind == ConversionKind.CONFORMS_TO) {
            return null;
        } else if (kind == ConversionKind.DATE_TO_UTC_MIDNIGHT) {
            return lib.toDate(value);
        } else {
            throw new DMNRuntimeException(String.format("'%s' is not supported yet", kind));
        }
    }

    private static boolean isSingletonList(Object value) {
        return value instanceof List && ((List) value).size() == 1;
    }
}
