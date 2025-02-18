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
package com.gs.dmn.feel.analysis.semantics.type;

import com.gs.dmn.context.DMNContext;
import com.gs.dmn.el.analysis.semantics.type.NullType;
import com.gs.dmn.el.analysis.semantics.type.Type;
import com.gs.dmn.feel.analysis.syntax.ast.expression.function.*;
import com.gs.dmn.runtime.DMNRuntimeException;
import com.gs.dmn.runtime.Pair;

import java.util.*;
import java.util.stream.Collectors;

import static com.gs.dmn.el.analysis.semantics.type.AnyType.ANY;
import static com.gs.dmn.feel.analysis.semantics.type.DateTimeType.DATE_AND_TIME;
import static com.gs.dmn.feel.analysis.semantics.type.DateType.DATE;
import static com.gs.dmn.feel.analysis.syntax.ast.expression.function.ConversionKind.*;

public abstract class FunctionType implements com.gs.dmn.el.analysis.semantics.type.FunctionType {
    public static final FunctionType ANY_FUNCTION = new FunctionType(Arrays.asList(), ANY) {
        @Override
        public boolean equivalentTo(Type other) {
            return false;
        }

        @Override
        public boolean conformsTo(Type other) {
            return false;
        }

        @Override
        public boolean match(ParameterTypes<Type, DMNContext> parameterTypes) {
            return false;
        }

        @Override
        protected List<Pair<ParameterTypes<Type, DMNContext>, ParameterConversions<Type, DMNContext>>> matchCandidates(List argumentTypes) {
            return null;
        }
    };

    protected final List<FormalParameter<Type, DMNContext>> parameters = new ArrayList<>();
    protected final List<Type> parameterTypes = new ArrayList<>();
    protected Type returnType;

    protected FunctionType(List<FormalParameter<Type, DMNContext>> parameters, Type returnType) {
        this.returnType = returnType;
        if (parameters != null) {
            this.parameters.addAll(parameters);
            this.parameterTypes.addAll(parameters.stream().map(FormalParameter::getType).collect(Collectors.toList()));
        }
    }

    public List<FormalParameter<Type, DMNContext>> getParameters() {
        return this.parameters;
    }

    public List<Type> getParameterTypes() {
        return this.parameterTypes;
    }

    @Override
    public Type getReturnType() {
        return this.returnType;
    }

    public void setReturnType(Type returnType) {
        if (returnType != null) {
            this.returnType = returnType;
        }
    }

    @Override
    public boolean isFullySpecified() {
        if (Type.isNull(this.returnType)) {
            return false;
        }
        return this.parameterTypes.stream().noneMatch(com.gs.dmn.el.analysis.semantics.type.Type::isNullOrAny)
                && !com.gs.dmn.el.analysis.semantics.type.Type.isNullOrAny(this.returnType);
    }

    public List<Pair<ParameterTypes<Type, DMNContext>, ParameterConversions<Type, DMNContext>>> matchCandidates(ParameterTypes<Type, DMNContext> parameterTypes) {
        if (parameterTypes instanceof PositionalParameterTypes) {
            List<Type> argumentTypes = ((PositionalParameterTypes<Type, DMNContext>) parameterTypes).getTypes();
            return matchCandidates(argumentTypes);
        } else {
            NamedParameterTypes<Type, DMNContext> namedSignature = (NamedParameterTypes<Type, DMNContext>) parameterTypes;
            List<Type> argumentTypes = new ArrayList<>();
            for(FormalParameter<Type, DMNContext> parameter: this.parameters) {
                Type type = namedSignature.getType(parameter.getName());
                if (!com.gs.dmn.el.analysis.semantics.type.Type.isNull(type)) {
                    argumentTypes.add(type);
                } else if (!(parameter.isOptional() || parameter.isVarArg())) {
                    return new ArrayList<>();
                }
            }
            return matchCandidates(argumentTypes);
        }
    }

    protected ArrayList<Pair<ParameterTypes<Type, DMNContext>, ParameterConversions<Type, DMNContext>>> calculateCandidates(List<Type> parameterTypes, List<Type> argumentTypes) {
        // calculate candidates
        Set<Pair<ParameterTypes<Type, DMNContext>, ParameterConversions<Type, DMNContext>>> candidates = new LinkedHashSet<>();
        int argumentSize = argumentTypes.size();
        ConversionKind[] candidateConversions = FUNCTION_RESOLUTION_CANDIDATES;
        int conversionSize = candidateConversions.length;

        // For every sequence of conversions, not including the exact match
        int[] conversionMap = init(argumentSize);
        conversionMap = next(conversionMap, argumentSize, conversionSize);
        while (conversionMap != null) {
            // Calculate new types and conversions for every argument
            List<Type> newTypes = new ArrayList<>();
            PositionalParameterConversions<Type, DMNContext> conversions = new PositionalParameterConversions<>();
            boolean different = false;
            for (int i = 0; i < argumentSize; i++) {
                // Compute new type and conversion
                ConversionKind kind = candidateConversions[conversionMap[i]];
                Type argumentType = argumentTypes.get(i);
                Type newType = argumentType;
                Conversion<Type> conversion = new Conversion<>(NONE, newType);
                if (i < parameterTypes.size()) {
                    Type parameterType = parameterTypes.get(i);
                    if (!com.gs.dmn.el.analysis.semantics.type.Type.conformsTo(argumentType, parameterType)) {
                        if (kind == NONE) {
                            // No conversion
                        } else if (kind == ELEMENT_TO_SINGLETON_LIST) {
                            // When the type of the expression is T and the target type is List<T> the expression is converted to a singleton list.
                            if (parameterType instanceof ListType) {
                                if (com.gs.dmn.el.analysis.semantics.type.Type.equivalentTo(argumentType, ((ListType) parameterType).getElementType())) {
                                    newType = new ListType(argumentType);
                                    conversion = new Conversion<>(kind, newType);

                                    different = true;
                                }
                            }
                        } else if (kind == SINGLETON_LIST_TO_ELEMENT) {
                            // When the type of the expression is List<T>, the value of the expression is a singleton list and the target type is T,
                            // the expression is converted by unwraping the first element.
                            if (argumentType instanceof ListType) {
                                if (com.gs.dmn.el.analysis.semantics.type.Type.equivalentTo(parameterType, ((ListType) argumentType).getElementType())) {
                                    newType = ((ListType) argumentType).getElementType();
                                    conversion = new Conversion<>(kind, newType);

                                    different = true;
                                }
                            }
                        } else if (kind == DATE_TO_UTC_MIDNIGHT) {
                            // When the type of the expression is date, the value of the expression is a date and the target type is date and time,
                            // the expression is converted to UTC midnight data and time.
                            if (com.gs.dmn.el.analysis.semantics.type.Type.equivalentTo(argumentType, DATE) && com.gs.dmn.el.analysis.semantics.type.Type.equivalentTo(parameterType, DATE_AND_TIME)) {
                                newType = DATE_AND_TIME;
                                conversion = new Conversion<>(kind, newType);

                                different = true;
                            }
                        } else if (kind == CONFORMS_TO) {
                            // When the type of the expression is T1, the target type is T2, and T1 conforms to T2 the value of expression
                            // remains unchanged. Otherwise the result is null.
                            if (!com.gs.dmn.el.analysis.semantics.type.Type.conformsTo(argumentType, parameterType)) {
                                newType = NullType.NULL;
                                conversion = new Conversion<>(kind, newType);

                                different = true;
                            }
                        } else {
                            throw new DMNRuntimeException(String.format("Conversion '%s' is not supported yet", kind));
                        }
                    }
                    newTypes.add(newType);
                    conversions.add(conversion);
                }
            }

            // Add new candidate
            if (different) {
                PositionalParameterTypes<Type, DMNContext> newSignature = new PositionalParameterTypes<>(newTypes);
                candidates.add(new Pair<>(newSignature, conversions));
            }

            // Next sequence
            conversionMap = next(conversionMap, argumentSize, conversionSize);
        }
        return new ArrayList<>(candidates);
    }

    // Initialise the sequence of vectors
    protected int[] init(int power) {
        int[] vector = new int[power];
        for (int i = 0; i < power; i++) {
            vector[i] = 0;
        }
        return vector;
    }

    // Generate next vector in sequence
    protected int[] next(int[] vector, int power, int cardinal) {
        for (int i = power - 1; i >= 0; i--) {
            if (vector[i] == cardinal - 1) {
                vector[i] = 0;
            } else {
                vector[i]++;
                break;
            }
        }
        // Check end of sequence
        boolean end = true;
        for (int i = 0; i < power; i++) {
            if (vector[i] != 0) {
                end = false;
                break;
            }
        }
        return end ? null : vector;
    }

    protected boolean compatible(ParameterTypes<Type, DMNContext> parameterTypes, List<FormalParameter<Type, DMNContext>> parameters) {
        if (parameterTypes instanceof PositionalParameterTypes) {
            return compatible((PositionalParameterTypes<Type, DMNContext>) parameterTypes, parameters);
        } else {
            return compatible((NamedParameterTypes<Type, DMNContext>) parameterTypes, parameters);
        }
    }

    private boolean compatible(PositionalParameterTypes<Type, DMNContext> parameterTypes, List<FormalParameter<Type, DMNContext>> parameters) {
        if (parameterTypes.size() != parameters.size()) {
            return false;
        }
        for (int i = 0; i < parameters.size(); i++) {
            Type formalParameterType = parameters.get(i).getType();
            Type argumentType = parameterTypes.getTypes().get(i);
            if (!com.gs.dmn.el.analysis.semantics.type.Type.conformsTo(argumentType, formalParameterType)) {
                return false;
            }
        }
        return true;
    }

    private boolean compatible(NamedParameterTypes<Type, DMNContext> parameterTypes, List<FormalParameter<Type, DMNContext>> parameters) {
        if (parameterTypes.size() != parameters.size()) {
            return false;
        }
        for (FormalParameter<Type, DMNContext> formalParameter : parameters) {
            Type argumentType = parameterTypes.getType(formalParameter.getName());
            Type parameterType = formalParameter.getType();
            if (!com.gs.dmn.el.analysis.semantics.type.Type.conformsTo(argumentType, parameterType)) {
                return false;
            }
        }
        return true;
    }

    public abstract boolean match(ParameterTypes<Type, DMNContext> parameterTypes);

    protected abstract List<Pair<ParameterTypes<Type, DMNContext>, ParameterConversions<Type, DMNContext>>> matchCandidates(List<Type> argumentTypes);
}
