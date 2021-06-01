package com.paulsoft.foodyeah.ServiceUnitTests;
import org.junit.Assert;
public class Util {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static void assertNotNull(String description, Object object) throws AssertionError{
        try{
            Assert.assertNotNull(description, object);
        }catch(AssertionError e){
            System.out.println(description + ANSI_RESET +" - "+ANSI_RED+"FAILED"+ANSI_RESET);
            throw e;
        }
    }
    public static void assertEquals(String description, Object object, Object objectaux) throws AssertionError{
        try{
            Assert.assertEquals(description, object,objectaux);
        }catch(AssertionError e){
            System.out.println(description + ANSI_RESET +" - "+ANSI_RED+"FAILED"+ANSI_RESET);
            throw e;
        }
    }
    public static void assertFalse(String description, Boolean flag) throws AssertionError{
        try{
            Assert.assertFalse(description, flag);
        }catch(AssertionError e){
            System.out.println(description + ANSI_RESET +" - "+ANSI_RED+"FAILED"+ANSI_RESET);
            throw e;
        }
    }
}
