package com.ducanh.utils;

public class NumberUtils {
    public static boolean checkNumber(String value) {
    	try {
    		Long number = Long.parseLong(value);
    	} catch (NumberFormatException ex) {
    		return false;
    	}
    	return true;
    }
}
