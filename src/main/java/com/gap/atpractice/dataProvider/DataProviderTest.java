package com.gap.atpractice.dataProvider;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

/**
 * Created by manuel on 29/05/17.
 */
public class DataProviderTest {

    /**
     * Creates a data provider element used by TestNG
     * @param m Method that could be used by data provider. Not required
     * @return An array of generic Java objects
     */
    @DataProvider(name = "dataProviderUser")
    public static Object[][] dataProviderUser(Method m) {
        System.out.println(String.format("Data Provider name: %s", m.getName()));

        return new Object[][]{
                {"test1", "test1"},
                {"test2", "test2"}
        };
    }
}
