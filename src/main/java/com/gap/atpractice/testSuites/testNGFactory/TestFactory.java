package com.gap.atpractice.testSuites.testNGFactory;

import com.gap.atpractice.testSuites.LoginTestNG;
import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;

/**
 * Created by manuel on 01/06/17.
 */

public class TestFactory {

    /**
     * Factory method, to generate multiple instances of the same page
     * @param executions Number of executions, or instances of the same page
     * @return An Object representing the page
     */
    @Factory
    @Parameters({"executions"})
    public Object[] loginFactory(int executions)
    {
        Object[] test = new Object[executions];

        for(int i = 0; i < executions; i++) {
            test[i] = new LoginTestNG();
        }

        return test;
    }
}
