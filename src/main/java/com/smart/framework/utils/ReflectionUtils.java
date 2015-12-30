package com.smart.framework.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @project Writing Java web frame from zero
 * @package com.smart.framework.utils
 * @file ReflectionUtils.java
 * @time 2015-12-22上午08:58:18
 * @description 
 * @author   nilu
 * @version 1.0
 *
 */
public class ReflectionUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtils.class);
    
    public static Object newInstance(Class<?> clazz) {
        Object instance = null;
        try {
            instance = clazz.newInstance();
        } catch (Exception e) {
            LOGGER.error(clazz.getName() + "instance class failure!", e);
            throw new RuntimeException(e);
        }
        return instance;
    }
    
    public static Object invokeMethod(Object obj, Method method, Object... args)  {
        try {
            return method.invoke(obj, args);
        }catch (Exception e) {
            LOGGER.error(obj.getClass().getName() + " invoke method " + method.getName() + " failure!", e);
            throw new RuntimeException(e);
        }
    }
    
    public static void setField(Object obj, Field field, Object value) {
        field.setAccessible(true);
        try {
            field.set(obj, value);
        } catch (Exception e) {
            LOGGER.error(obj.getClass().getName() + " set field " + field.getName() + " failure!", e);
            throw new RuntimeException(e);
        }
    }
}
