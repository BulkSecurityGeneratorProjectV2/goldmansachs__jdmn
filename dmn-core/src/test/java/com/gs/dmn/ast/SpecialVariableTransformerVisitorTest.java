package com.gs.dmn.ast;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpecialVariableTransformerVisitorTest {
    private final SpecialVariableTransformerVisitor visitor = new SpecialVariableTransformerVisitor();

    @Test
    public void testUpdateUnaryTests() {
        // TODO Add more tests
        doTest("contains(x.y.z, \"123\")", "x.y.z", "contains(?, \"123\")");
        doTest("review.nextActionNew", "review.nextAction", "review.nextActionNew");
    }

    private void doTest(String unaryTestsText, String inputExpression, String expectedValue) {
        TUnaryTests unaryTests = new TUnaryTests();
        unaryTests.setText(unaryTestsText);
        visitor.updateUnaryTests(unaryTests, inputExpression);
        assertEquals(expectedValue, unaryTests.getText());
    }
}