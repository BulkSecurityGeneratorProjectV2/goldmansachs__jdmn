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
package com.gs.dmn.ast;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "name",
        "id",
        "label",
        "expressionLanguage",
        "otherAttributes",
        "description",
        "namespace",
        "locationURI",
        "importType",
        "importedElement",
        "extensionElements"
})
public class TImportedValues extends TImport implements Visitable {
    private String importedElement;
    private String expressionLanguage;

    public String getImportedElement() {
        return importedElement;
    }

    public void setImportedElement(String value) {
        this.importedElement = value;
    }

    public String getExpressionLanguage() {
        return expressionLanguage;
    }

    public void setExpressionLanguage(String value) {
        this.expressionLanguage = value;
    }

    @Override
    public <C> Object accept(Visitor visitor, C context) {
        return visitor.visit(this, context);
    }
}
