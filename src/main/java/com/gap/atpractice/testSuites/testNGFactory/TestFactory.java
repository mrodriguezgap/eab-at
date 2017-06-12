package com.gap.atpractice.testNGFactory;

import com.gap.atpractice.testSuites.LoginTestNG;
import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;

/**
 * Created by auto on 01/06/17.
 */

public class TestFactory {

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
