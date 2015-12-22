package com.smart.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @project Writing Java web frame from zero
 * @package com.smart.framework.annotation
 * @file Controller.java
 * @time 2015-12-21下午10:25:27
 * @description 
 * @author   nilu
 * @version 1.0
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
    String value();
}
