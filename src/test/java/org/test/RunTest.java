package org.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RunTest {

    @BeforeMethod
    public void preCondition(){
        System.out.println("Pre-condition");
    }

    @Test
    public void testOne(){
        System.out.println("Test One");
    }

    @AfterMethod
    public void cleanUp(){
        System.out.println("In clean up");
    }


}
