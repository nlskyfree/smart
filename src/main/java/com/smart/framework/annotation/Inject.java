package com.smart.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @project Writing Java web frame from zero
 * @package com.smart.framework.annotation
 * @file Inject.java
 * @time 2015-12-21下午10:30:35
 * @description 
 * @author   nilu
 * @version 1.0
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {

}
