package com.smart.framework.helper;

import java.util.HashSet;
import java.util.Set;

import com.smart.framework.annotation.Controller;
import com.smart.framework.annotation.Service;
import com.smart.framework.utils.ClassUtils;
import com.smart.framework.utils.GyUtils;

/**
 *
 * @project Writing Java web frame from zero
 * @package com.smart.framework.helper
 * @file ClassHelper.java
 * @time 2015-12-22上午08:24:29
 * @description 
 * @author   nilu
 * @version 1.0
 *
 */
public class ClassHelper {
    private static final Set<Class<?>> CLASS_SET;
    
    static {
        final String appBasePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtils.getClassSet(appBasePackage);
    }
    
    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }
    
    public static Set<Class<?>> getServiceClassSet() {
        if(GyUtils.isNull(CLASS_SET)) {
            return null;
        }
        Set<Class<?>> serviceClassSet = new HashSet<Class<?>>();
        for(Class<?> clazz : CLASS_SET) {
            if(clazz.isAnnotationPresent(Service.class)) {
                serviceClassSet.add(clazz);
            }
        }
        return serviceClassSet;
    }
    
    public static Set<Class<?>> getControllerClassSet() {
        if(GyUtils.isNull(CLASS_SET)) {
            return null;
        }
        Set<Class<?>> controllerClassSet = new HashSet<Class<?>>();
        for(Class<?> clazz : CLASS_SET) {
            if(clazz.isAnnotationPresent(Controller.class)) {
                controllerClassSet.add(clazz);
            }
        }
        return controllerClassSet;
    }
    
    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
        if(GyUtils.isNull(getControllerClassSet())) {
            beanClassSet.addAll(getControllerClassSet());
        }
        if(GyUtils.isNull(getServiceClassSet())) {
            beanClassSet.addAll(getServiceClassSet());
        }
        return beanClassSet;
    }
}
