/**
 * 
 */
package com.smart.framework.bean;

/**
 *
 * @project Writing Java web frame from zero
 * @package com.smart.framework.bean
 * @file Request.java
 * @time 2015-12-29下午05:21:38
 * @description 
 * @author   nilu
 * @version 1.0
 *
 */
public class Request {
    
    private String requestPath;
    private String requestMethod;
    
    public Request(String requestPath, String requestMethod) {
        this.requestPath = requestPath;
        this.requestMethod = requestMethod;
    }
    
    /**
     * @time2015-12-29下午05:23:17
     *get
     * @return the requestPath
     */
    public String getRequestPath() {
        return requestPath;
    }
    /**
     * @time 2015-12-29下午05:23:17
     * set
     * @param requestPath the requestPath to set
     */
    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }
    /**
     * @time2015-12-29下午05:23:17
     *get
     * @return the requestMethod
     */
    public String getRequestMethod() {
        return requestMethod;
    }
    /**
     * @time 2015-12-29下午05:23:17
     * set
     * @param requestMethod the requestMethod to set
     */
    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }
}
