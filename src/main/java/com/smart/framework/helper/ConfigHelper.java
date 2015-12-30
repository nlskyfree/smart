package com.smart.framework.helper;

import java.util.Properties;

import com.smart.framework.constants.ConfigConstant;
import com.smart.framework.utils.PropsUtils;

/**
 *
 * @project Writing Java web frame from zero
 * @package com.smart.framework.utils
 * @file ConfigUtils.java
 * @time 2015-12-17下午04:35:48
 * @description 
 * @author   nilu
 * @version 1.0
 *
 */
public class ConfigHelper {
    private final static Properties props = PropsUtils.loadProps("smart.properties");
    
    public static String getJdbcDriver() {
        return PropsUtils.getString(props, ConfigConstant.JDBC_DRIVER);
    }
    
    public static String getJdbcUrl() {
        return PropsUtils.getString(props, ConfigConstant.JDBC_URL);
    }
    
    public static String getJdbcUsername() {
        return PropsUtils.getString(props, ConfigConstant.JDBC_USERNAME);
    }
    
    public static String getJdbcPassword() {
        return PropsUtils.getString(props, ConfigConstant.JDBC_PASSWORD);
    }
    
    public static String getAppBasePackage() {
        return PropsUtils.getString(props, ConfigConstant.APP_BASE_PACKAGE);
    }
    
    public static String getAppJspPath() {
        return PropsUtils.getString(props, ConfigConstant.APP_JSP_PATH);
    }

    public static String getAppAssetPath() {
        return PropsUtils.getString(props, ConfigConstant.APP_Asset_PATH);
    }
}
