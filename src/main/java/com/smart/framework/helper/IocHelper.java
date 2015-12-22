package com.smart.framework.helper;

import java.lang.reflect.Field;
import java.util.Map;

import com.smart.framework.annotation.Inject;
import com.smart.framework.utils.GyUtils;
import com.smart.framework.utils.ReflectionUtils;

/**
 *
 * @project Writing Java web frame from zero
 * @package com.smart.framework.helper
 * @file IocHelper.java
 * @time 2015-12-22下午07:23:54
 * @description 
 * @author   nilu
 * @version 1.0
 *
 */
public class IocHelper {
    static {
        //获取所有需要注入的Bean
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        //遍历BeanMap
        if(!GyUtils.isNull(beanMap)) {
            for(Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
                Class<?> clazz = entry.getKey();
                Object beanInstance = entry.getValue();
                //获取Bean所有成员变量
                Field[] fields = clazz.getDeclaredFields();
                if(!GyUtils.isNull(fields)) {
                    //遍历Bean Field
                    for(Field field : fields) {
                        //判断是否有注解标记，是否需要注入
                        if(field.getType().isAnnotationPresent(Inject.class)) {
                            Object fieldInstance = beanMap.get(field.getType());
                            //注入字段
                            if(!GyUtils.isNull(fieldInstance)) {
                                ReflectionUtils.setField(beanInstance, field, fieldInstance);
                            } else {
                                throw new RuntimeException(beanInstance.getClass().getName() + "bean member variable have not been managered by IOC container!");
                            }
                        }
                    }
                }
            }
        }
    }
}
