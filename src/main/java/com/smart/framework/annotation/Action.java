package com.smart.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @project Writing Java web frame from zero
 * @package com.smart.framework.annotation
 * @file Action.java
 * @time 2015-12-21下午10:27:28
 * @description 
 * @author   nilu
 * @version 1.0
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    String value();
}
