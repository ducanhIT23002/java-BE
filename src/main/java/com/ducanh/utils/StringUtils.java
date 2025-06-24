package com.ducanh.utils;

public class StringUtils {
    public static boolean checkString(String value) {
    	if (value != null && !value.equals("")) {
    		return true;
    	} else return false;
    }
}
