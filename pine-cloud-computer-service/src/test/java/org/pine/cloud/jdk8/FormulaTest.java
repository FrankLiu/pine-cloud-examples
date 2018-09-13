package org.pine.cloud.jdk8;

import org.junit.Assert;
import org.junit.Test;

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

        Assert.assertTrue(100 == f.calculate(100));
        Assert.assertTrue(4 == f.sqrt(16));
    }
}
