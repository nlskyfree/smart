package com.smart.framework.bean;

import java.lang.reflect.Method;

/**
 *
 * @project Writing Java web frame from zero
 * @package com.smart.framework.bean
 * @file Handler.java
 * @time 2015-12-29下午05:24:36
 * @description 
 * @author   nilu
 * @version 1.0
 *
 */
public class Handler {
    private Class<?> controllerClass;
    private Method method;
    
    public Handler(Class<?> controllerClass, Method method) {
        this.controllerClass = controllerClass;
        this.method = method;
    }

    /**
     * @time2015-12-29下午05:27:39
     *get
     * @return the controllerClass
     */
    public Class<?> getControllerClass() {
        return controllerClass;
    }

    /**
     * @time 2015-12-29下午05:27:39
     * set
     * @param controllerClass the controllerClass to set
     */
    public void setControllerClass(Class<?> controllerClass) {
        this.controllerClass = controllerClass;
    }

    /**
     * @time2015-12-29下午05:27:40
     * get
     * @return the method
     */
    public Method getMethod() {
        return method;
    }

    /**
     * @time 2015-12-29下午05:27:40
     * set
     * @param method the method to set
     */
    public void setMethod(Method method) {
        this.method = method;
    }
}
