package com.smart.framework.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @project Writing Java web frame from zero
 * @package org.smart4j.utils
 * @file PropsUtils.java
 * @time 2015-12-16下午09:40:34
 * @description utils for loading .properties
 * @author   nilu
 * @version 1.0
 *
 */
public class PropsUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtils.class);
    
    /**
     * @description load properties by fileName
     * @time 2015-12-16下午09:40:58
     * @param fileName
     * @return
     * @author   nilu
     */
    public static Properties loadProps(String fileName) {
        Properties props = null;
        InputStream in = null;
        try {
            in = PropsUtils.class.getClassLoader().getResourceAsStream(fileName);
            if(in == null) {
                throw new FileNotFoundException(fileName + " file it not found");
            }
            props = new Properties();
            props.load(in);
        } catch(IOException e) {
            LOGGER.error("load properties file failure", e);
        } finally {
            try {
                in.close();
            } catch(IOException e) {
                LOGGER.error("close input stream failure" + e);
            }
        }
        
        return props;
    }
    
    /**
     * @description get string value from properties
     * @time 2015-12-16下午09:41:24
     * @param props
     * @param key
     * @return
     * @author   nilu
     */
    public static String getString(Properties props, String key) {
        return getString(props, key, "");
    }
    
    /**
     * @description get string value from properties with defalut value
     * @time 2015-12-16下午09:41:24
     * @param props
     * @param key
     * @return
     * @author   nilu
     */
    public static String getString(Properties props, String key, String defaultValue) {
        String value = defaultValue;
        if(!GyUtils.isNull(props) && !GyUtils.isNull(key)) {
            if(props.containsKey(key)) {
                value = props.getProperty(key);
            }
        }
        return value;
    }
    
    /**
     * @description get int value from properties
     * @time 2015-12-16下午09:41:24
     * @param props
     * @param key
     * @return
     * @author   nilu
     */
    public static int getInt(Properties props, String key) {
        return getInt(props, key, 0);
    }

    /**
     * @description get int value from properties with defalut value
     * @time 2015-12-16下午09:41:24
     * @param props
     * @param key
     * @return
     * @author   nilu
     */
    public static int getInt(Properties props, String key, int defaultValue) {
        int value = defaultValue;
        if(!GyUtils.isNull(props) && !GyUtils.isNull(key)) {
            if(props.containsKey(key)) {
                value = CastUtils.castInt(props.getProperty(key));
            }
        }
        return value;
    }
    
    /**
     * @description get boolean value from properties
     * @time 2015-12-16下午09:41:24
     * @param props
     * @param key
     * @return
     * @author   nilu
     */
    public static boolean getBoolean(Properties props, String key) {
        return getBoolean(props, key, false);
    }

    /**
     * @description get boolean value from properties with defalut value
     * @time 2015-12-16下午09:41:24
     * @param props
     * @param key
     * @return
     * @author   nilu
     */
    public static boolean getBoolean(Properties props, String key, boolean defaultValue) {
        boolean value = defaultValue;
        if(!GyUtils.isNull(props) && !GyUtils.isNull(key)) {
            if(props.containsKey(key)) {
                value = CastUtils.castBoolean(props.getProperty(key));
            }
        }
        return value;
    }
    
    /**
     * @description get double value from properties
     * @time 2015-12-16下午09:41:24
     * @param props
     * @param key
     * @return
     * @author   nilu
     */
    public static double getDouble(Properties props, String key) {
        return getDouble(props, key , 0.0);
    }

    /**
     * @description get double value from properties with defalut value
     * @time 2015-12-16下午09:41:24
     * @param props
     * @param key
     * @return
     * @author   nilu
     */
    public static double getDouble(Properties props, String key, double defaultValue) {
        double value = defaultValue;
        if(!GyUtils.isNull(props) && !GyUtils.isNull(key)) {
            if(props.containsKey(key)) {
                value = CastUtils.castDouble(props.getProperty(key));
            }
        }
        return value;
    }
    
}
