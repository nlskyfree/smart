package com.smart.framework.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.smart.framework.annotation.Action;
import com.smart.framework.bean.Handler;
import com.smart.framework.bean.Request;
import com.smart.framework.utils.GyUtils;

/**
 *
 * @project Writing Java web frame from zero
 * @package com.smart.framework.helper
 * @file ControllerHelper.java
 * @time 2015-12-29下午05:29:25
 * @description 
 * @author   nilu
 * @version 1.0
 *
 */
public class ControllerHelper {
    private final static Map<Request, Handler> HANDLER_MAP = new HashMap<Request, Handler>();
    
    static {
        //得到所有包含@Controller注解的字节码文件
        final Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if(!GyUtils.isNull(controllerClassSet)) {
            for(Class<?> clazz : controllerClassSet) {
                //获取所有注解为@Action的方法
                Method[] methods = clazz.getDeclaredMethods();
                if(!GyUtils.isNull(methods)) {
                    for(Method method : methods) {
                        if(method.isAnnotationPresent(Action.class)) {
                            Action action = method.getAnnotation(Action.class);
                            Request request = getRequestBean(action.value());     
                            Handler handler = new Handler(clazz, method);
                            //建立映射关系
                            HANDLER_MAP.put(request, handler);
                        }
                    }
                }
            }
        }
    }

    /**
     * @description get request by the param of action
     * @time 2015-12-29下午05:44:52
     * @param value
     * @return Request
     * @author   nilu
     */
    private static Request getRequestBean(String value) {
        String[] urls = value.split(":");
        return new Request(urls[0], urls[1]);
    }
    
    /**
     * @description get handler by request
     * @time 2015-12-29下午07:17:39
     * @param request
     * @return
     * @author   nilu
     */
    public static Handler getHandler(Request request) {
        return HANDLER_MAP.get(request);
    }
}
