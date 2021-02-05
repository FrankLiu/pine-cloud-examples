package org.pine.cloud.jdk8;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author liujun
 * @sinace 2017/11/30 0030.
 */
public class FormulaTest {
    @Test
    public void testCalculate() {
        Formula f = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        Assertions.assertTrue(100 == f.calculate(100));
        Assertions.assertTrue(4 == f.sqrt(16));
    }
}
