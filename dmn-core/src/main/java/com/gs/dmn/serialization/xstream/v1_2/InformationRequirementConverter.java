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
package com.gs.dmn.serialization.xstream.v1_2;

import com.gs.dmn.ast.DMNBaseElement;
import com.gs.dmn.ast.TDMNElementReference;
import com.gs.dmn.ast.TInformationRequirement;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class InformationRequirementConverter extends DMNElementConverter {
    private static final String REQUIRED_INPUT = "requiredInput";
    private static final String REQUIRED_DECISION = "requiredDecision";

    public InformationRequirementConverter(XStream xstream) {
        super(xstream);
    }

    @Override
    public boolean canConvert(Class clazz) {
        return clazz.equals(TInformationRequirement.class);
    }

    @Override
    protected DMNBaseElement createModelObject() {
        return new TInformationRequirement();
    }

    @Override
    protected void assignChildElement(Object parent, String nodeName, Object child) {
        TInformationRequirement ir = (TInformationRequirement) parent;

        if (REQUIRED_INPUT.equals(nodeName)) {
            ir.setRequiredInput((TDMNElementReference) child);
        } else if (REQUIRED_DECISION.equals(nodeName)) {
            ir.setRequiredDecision((TDMNElementReference) child);
        } else {
            super.assignChildElement(parent, nodeName, child);
        }
    }

    @Override
    protected void assignAttributes(HierarchicalStreamReader reader, Object parent) {
        super.assignAttributes(reader, parent);
    }

    @Override
    protected void writeChildren(HierarchicalStreamWriter writer, MarshallingContext context, Object parent) {
        super.writeChildren(writer, context, parent);
        TInformationRequirement ir = (TInformationRequirement) parent;

        if (ir.getRequiredDecision() != null) {
            writeChildrenNode(writer, context, ir.getRequiredDecision(), REQUIRED_DECISION);
        }
        if (ir.getRequiredInput() != null) {
            writeChildrenNode(writer, context, ir.getRequiredInput(), REQUIRED_INPUT);
        }
    }

    @Override
    protected void writeAttributes(HierarchicalStreamWriter writer, Object parent) {
        super.writeAttributes(writer, parent);

        // no attributes.
    }


}
