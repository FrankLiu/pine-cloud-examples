package org.pine.cloud.jdk8;

/**
 *
 * @author liujun
 * @sinace 2017/11/30 0030.
 */
interface Formula {
    double calculate(int a);

    default double sqrt(int a){
        return Math.sqrt(a);
    }
}
