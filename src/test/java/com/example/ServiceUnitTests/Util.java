package com.example.ServiceUnitTests;

import org.junit.Assert;

public class Util {
    public static void assertNotNull(String description, Object object) throws Exception{
        try{
            Assert.assertNotNull(description, object);
            System.out.println(description + " - PASSED");
        }catch(AssertionError e){
            System.out.println(description + " - FAILED");
            throw e;
        }
    }
    public static void assertEquals(String description, Object object, Object objectaux) throws Exception{
        try{
            Assert.assertEquals(description, object,objectaux);
            System.out.println(description + " - PASSED");
        }catch(AssertionError e){
            System.out.println(description + " - FAILED");
            throw e;
        }
    }
    public static void assertFalse(String description, Boolean flag) throws Exception{
        try{
            Assert.assertFalse(description, flag);
            System.out.println(description + " - PASSED");
        }catch(AssertionError e){
            System.out.println(description + " - FAILED");
            throw e;
        }
    }
}
