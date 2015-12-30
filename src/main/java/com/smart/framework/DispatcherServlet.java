package com.smart.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smart.framework.bean.Data;
import com.smart.framework.bean.Handler;
import com.smart.framework.bean.Request;
import com.smart.framework.bean.View;
import com.smart.framework.helper.BeanHelper;
import com.smart.framework.helper.ConfigHelper;
import com.smart.framework.helper.ControllerHelper;
import com.smart.framework.utils.GyUtils;
import com.smart.framework.utils.JsonUtils;
import com.smart.framework.utils.ReflectionUtils;

/**
 *
 * @project Writing Java web frame from zero
 * @package com.smart.framework
 * @file DispatchServlet.java
 * @time 2015-12-30上午09:00:12
 * @description 
 * @author   nilu
 * @version 1.0
 *
 */
@WebServlet(urlPatterns="/*", loadOnStartup=0)
public class DispatcherServlet extends HttpServlet {
    
    /**
     * @description 
     * @value value:serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * @description 
     * @time 2015-12-30下午05:17:32
     * @param config
     * @throws ServletException
     * @author   nilu
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        //初始化组件
        HelperLoader.init();
        //注册servlet
        ServletContext servletContext = config.getServletContext();
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
        
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }

    /**
     * @description 
     * @time 2015-12-30下午05:17:32
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     * @author   nilu
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String requestMethod = req.getMethod().toLowerCase();
        String requestPath = req.getPathInfo();
        
        //根据request得到handler处理器
        Handler handler = ControllerHelper.getHandler(new Request(requestMethod, requestPath));
        //获取方法参数
        Object bean = BeanHelper.getBean(handler.getControllerClass());
        //调用方法，得到返回值
        Object result = ReflectionUtils.invokeMethod(bean, handler.getMethod(), req.getParameterMap());
        if(result instanceof View) {
            View view = (View) result;
            String path = view.getPath();
            if(!GyUtils.isNull(path)) {
                if(path.startsWith("/")) {
                    res.sendRedirect(req.getContextPath() + path);
                } else { 
                    Map<String, Object> modelMap = view.getModel();
                    if(!GyUtils.isNull(modelMap)) {
                        for(Map.Entry<String, Object> entry : modelMap.entrySet()) {
                            req.setAttribute(entry.getKey(), entry.getValue());
                        }
                    }
                    req.getRequestDispatcher(path).forward(req, res);
                }
            }
        } else if(result instanceof Data) {
            //json数据放进输出流
            Data data = (Data) result;
            Object model = data.getModel();
            if(!GyUtils.isNull(model)) {
                PrintWriter writer = res.getWriter();
                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");
                String json = JsonUtils.objectToJsonString(model);
                writer.write(json);
                writer.flush();
                writer.close();
            }
        } else {
            throw new RuntimeException("the return value of the controller should be the View or the Data");
        }
    }
}
