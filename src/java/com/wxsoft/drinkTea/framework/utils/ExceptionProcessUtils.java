package com.wxsoft.drinkTea.framework.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;


public class ExceptionProcessUtils {

public final static int DEFAULT_MAX_LENGTH = 1024 + 512;
    
    public static String getStack(Throwable e , int length) {
        String result = ExceptionUtils.getStackTrace(e);
        if(result.length() > length) {
            return result.substring(0 , length);
        }
        return result;
    }
    
    public static String getStack(Throwable e) {
        return getStack(e , DEFAULT_MAX_LENGTH);
    }
}
