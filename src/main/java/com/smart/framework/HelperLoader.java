package com.smart.framework;

import com.smart.framework.helper.BeanHelper;
import com.smart.framework.helper.ClassHelper;
import com.smart.framework.helper.ControllerHelper;
import com.smart.framework.helper.IocHelper;
import com.smart.framework.utils.ClassUtils;

/**
 *
 * @project Writing Java web frame from zero
 * @package com.smart.framework
 * @file HelperLoader.java
 * @time 2015-12-29下午07:07:20
 * @description 
 * @author   nilu
 * @version 1.0
 *
 */
public class HelperLoader {
    static {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for(Class<?> clazz : classList) {
            ClassUtils.loadClass(clazz.getName());
        }
    }
}
