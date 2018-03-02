/**
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
package com.gs.dmn.interpreter;

import org.junit.Test;

public abstract class CL2SDMNInterpreterTest extends AbstractDMNInterpreterTest {
    @Override
    protected String getDMNInputPath() {
        return "tck/cl2";
    }

    @Override
    protected String getTestCasesInputPath() {
        return getDMNInputPath();
    }

    @Test
    public void testCL2() throws Exception {
        doTestDiagram("0001-input-data-string");
        doTestDiagram("0002-input-data-number");
        doTestDiagram("0003-input-data-string-allowed-values");
        doTestDiagram("0004-simpletable-U");
        doTestDiagram("0005-simpletable-A");
        doTestDiagram("0006-simpletable-P1");
        doTestDiagram("0007-simpletable-P2");
        doTestDiagram("0008-LX-arithmetic");
        doTestDiagram("0009-invocation-arithmetic");
        doTestDiagram("0010-multi-output-U");
        doTestDiagram("0100-feel-constants");
        doTestDiagram("0101-feel-constants");
        doTestDiagram("0102-feel-constants");
        doTestDiagram("0105-feel-math");
        doTestDiagram("0106-feel-ternary-logic");
        doTestDiagram("0107-feel-ternary-logic-not");
    }
}