package com.smart.framework.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @project Writing Java web frame from zero
 * @package org.smart4j.utils
 * @file GyUtils.java
 * @time 2015-12-16下午09:38:09
 * @description 
 * @author   nilu
 * @version 1.0
 *
 */
public class GyUtils {
    
    /**
     * @description is Null for object
     * @time 2015-12-16下午09:38:00
     * @param object
     * @return
     * @author   nilu
     */
    public static boolean isNull(Object object) {
        boolean flag = true;
        if(null != object) {
            if(object instanceof Object[]) {
                flag = isNull((Object[])object);
            } else if(object instanceof String) {
                flag = isNull((Object[])object);
            } else if(object instanceof List<?>) {
                flag = isNull((List<?>)object);
            } else if(object instanceof Map<?,?>) {
                flag = isNull((Map<?,?>)object);
            } else if(object instanceof Set<?>) {
                flag = isNull((Set<?>)object);
            } else {
                flag = false;
            }
        }
        return flag;
    }
    
    /**
     * @description is Null for object[]
     * @time 2015-12-16下午09:36:51
     * @param objects
     * @return
     * @author   nilu
     */
    public static boolean isNull(Object[] objects) {
        return null == objects || 0 == objects.length;
    }
    
    /**
     * @description is Null for string
     * @time 2015-12-16下午09:37:07
     * @param string
     * @return
     * @author   nilu
     */
    public static boolean isNull(String string) {
        return null == string || "".equals(string);
    }
    
    /**
     * @description is Null for List
     * @time 2015-12-16下午09:37:17
     * @param list
     * @return
     * @author   nilu
     */
    public static boolean isNull(List<?> list) {
        return null == list || list.isEmpty();
    } 
    
    /**
     * @description is Null for map
     * @time 2015-12-16下午09:37:34
     * @param map
     * @return
     * @author   nilu
     */
    public static boolean isNull(Map<?,?> map) {
        return null == map || map.isEmpty();
    }
    
    /**
     * @description is Null for set
     * @time 2015-12-16下午09:37:43
     * @param set
     * @return
     * @author   nilu
     */
    public static boolean isNull(Set<?> set) {
        return null == set || set.isEmpty();
    }
}
