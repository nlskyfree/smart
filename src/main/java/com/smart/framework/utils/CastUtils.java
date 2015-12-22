package com.smart.framework.utils;

/**
 *
 * @project Writing Java web frame from zero
 * @package org.smart4j.utils
 * @file CastUtils.java
 * @time 2015-12-16下午09:38:24
 * @description utils for cast
 * @author   nilu
 * @version 1.0
 *
 */
public class CastUtils {
    
    /**
     * @description object cast to string
     * @time 2015-12-16下午09:38:37
     * @param value
     * @return
     * @author   nilu
     */
    public static String castString(Object value) {
        return castString(value, "");
    }
    
    /**
     * @description object cast to string
     * @time 2015-12-16下午09:38:37
     * @param value
     * @return
     * @author   nilu
     */
    public static String castString(Object value, String defaultValue) {
        return GyUtils.isNull(value) ? defaultValue : String.valueOf(value);
    }
    
    /**
     * @description object cast to int
     * @time 2015-12-16下午09:38:51
     * @param value
     * @return
     * @author   nilu
     */
    public static int castInt(Object value) {
        return castInt(value, 0);
    }

    /**
     * @description object cast to int
     * @time 2015-12-16下午09:38:51
     * @param value
     * @return
     * @author   nilu
     */
    public static int castInt(Object value, int defaultValue) {
        int intValue = defaultValue;
        String strValue = castString(value);
        if(!GyUtils.isNull(value)) {
            intValue = Integer.parseInt(strValue);
        }
        return intValue;
    }
    
    /**
     * @description object cast to long
     * @time 2015-12-16下午09:39:05
     * @param value
     * @return
     * @author   nilu
     */
    public static long castLong(Object value) {
        return castLong(value, 0);
    }

    /**
     * @description object cast to long
     * @time 2015-12-16下午09:39:15
     * @param value
     * @param defaultValue
     * @return
     * @author   nilu
     */
    public static long castLong(Object value, long defaultValue) {
        return 0;
    }

    /**
     * @description object cast to boolean
     * @time 2015-12-16下午09:39:15
     * @param value
     * @param defaultValue
     * @return
     * @author   nilu
     */
    public static boolean castBoolean(Object value) {
        return castBoolean(value, false);
    }

    /**
     * @description object cast to boolean
     * @time 2015-12-16下午09:39:15
     * @param value
     * @param defaultValue
     * @return
     * @author   nilu
     */
    public static boolean castBoolean(Object value, boolean defaultValue) {
        boolean booleanValue = defaultValue;
        String strValue = castString(value);
        if(!GyUtils.isNull(strValue)) {
            booleanValue = Boolean.parseBoolean(strValue);
        }
        return booleanValue;
    }
    
    /**
     * @description object cast to float
     * @time 2015-12-16下午09:39:15
     * @param value
     * @param defaultValue
     * @return
     * @author   nilu
     */
    public static float castFloat(Object value) {
        return castFloat(value, 0.0f);
    }

    /**
     * @description object cast to float
     * @time 2015-12-16下午09:39:15
     * @param value
     * @param defaultValue
     * @return
     * @author   nilu
     */
    public static float castFloat(Object value, float defaultValue) {
        float floatValue = defaultValue;
        String strValue = castString(value);
        if(!GyUtils.isNull(strValue)) {
            try {
                floatValue = Float.parseFloat(strValue);
            } catch(NumberFormatException e) {
                floatValue = defaultValue;
            }
        }
        return floatValue;
    }

    /**
     * @description object cast to double
     * @time 2015-12-16下午09:39:15
     * @param value
     * @param defaultValue
     * @return
     * @author   nilu
     */
    public static double castDouble(Object value) {
        return castDouble(value, 0.0);
    }

    /**
     * @description object cast to double
     * @time 2015-12-16下午09:39:15
     * @param value
     * @param defaultValue
     * @return
     * @author   nilu
     */
    public static double castDouble(Object value, double defaultValue) {
        double doubleValue = defaultValue;
        String strValue = castString(value);
        if(!GyUtils.isNull(strValue)) {
            try {
                doubleValue = Double.parseDouble(strValue);
            } catch(NumberFormatException e) {
                doubleValue = defaultValue;
            }
        }
        return doubleValue;
    }
}
