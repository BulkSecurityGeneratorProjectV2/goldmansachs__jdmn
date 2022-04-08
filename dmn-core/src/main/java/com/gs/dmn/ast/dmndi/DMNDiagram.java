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
package com.gs.dmn.ast.dmndi;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gs.dmn.ast.Visitable;
import com.gs.dmn.ast.Visitor;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "name",
        "id",
        "sharedStyle",
        "otherAttributes",
        "style",
        "extension",
        "documentation",
        "resolution",
        "size",
        "dmnDiagramElement"
})
public class DMNDiagram<C> extends Diagram<C> implements Visitable<C> {
    private Dimension<C> size;
    private List<? extends DiagramElement<C>> dmnDiagramElement;

    public Dimension<C> getSize() {
        return size;
    }

    public void setSize(Dimension<C> value) {
        this.size = value;
    }

    public List<? extends DiagramElement<C>> getDMNDiagramElement() {
        if (dmnDiagramElement == null) {
            dmnDiagramElement = new ArrayList<>();
        }
        return this.dmnDiagramElement;
    }

    @Override
    public Object accept(Visitor<C> visitor, C context) {
        return visitor.visit(this, context);
    }
}