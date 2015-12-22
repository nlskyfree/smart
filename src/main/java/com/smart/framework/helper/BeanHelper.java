package com.smart.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smart.framework.utils.GyUtils;
import com.smart.framework.utils.ReflectionUtils;

/**
 *
 * @project Writing Java web frame from zero
 * @package com.smart.framework.helper
 * @file BeanHelper.java
 * @time 2015-12-22下午06:49:26
 * @description 
 * @author   nilu
 * @version 1.0
 *
 */
public class BeanHelper {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanHelper.class);
    
    private static final Map<Class<?>, Object> BEAN_MAP;
    
    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        BEAN_MAP = new HashMap<Class<?>, Object>();
        if(GyUtils.isNull(beanClassSet)) {
            for(Class<?> clazz : beanClassSet) {
                //建立字节码与对应Bean的映射关系
                BEAN_MAP.put(clazz, ReflectionUtils.newInstance(clazz));
            }
        }
    }
    
    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        if(!BEAN_MAP.containsKey(clazz)) {
            LOGGER.error("can not get bean by class" + clazz);
            throw new RuntimeException("can not get bean by class");
        }
        return (T) BEAN_MAP.get(clazz);
    }
}
