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
package com.gs.dmn.feel.analysis.syntax.ast.expression.textual;

import com.gs.dmn.feel.analysis.syntax.ast.Visitor;
import com.gs.dmn.feel.analysis.syntax.ast.expression.Expression;

import java.util.Objects;

public class FilterExpression<T, C> extends Expression<T, C> {
    public static final String FILTER_PARAMETER_NAME = "item";

    private final Expression<T, C> source;
    private Expression<T, C> filter;

    public FilterExpression(Expression<T, C> source, Expression<T, C> filter) {
        this.source = source;
        this.filter = filter;
    }

    public Expression<T, C> getSource() {
        return this.source;
    }

    public Expression<T, C> getFilter() {
        return this.filter;
    }

    public void setFilter(Expression<T, C> filter) {
        this.filter = filter;
    }

    @Override
    public Object accept(Visitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterExpression<?, ?> that = (FilterExpression<?, ?>) o;
        return Objects.equals(source, that.source) && Objects.equals(filter, that.filter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, filter);
    }

    @Override
    public String toString() {
        return String.format("%s(%s, %s)", getClass().getSimpleName(), this.source.toString(), this.filter.toString());
    }
}
