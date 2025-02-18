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
package com.gs.dmn.feel.analysis.syntax.ast.expression;

import com.gs.dmn.feel.analysis.syntax.ast.Element;

public abstract class Expression<T, C> extends Element<T, C> implements com.gs.dmn.el.analysis.syntax.ast.expression.Expression<T, C> {
    private T type;

    @Override
    public T getType() {
        return type;
    }

    @Override
    public void setType(T type) {
        if (type != null) {
            this.type = type;
        }
    }
}
 
