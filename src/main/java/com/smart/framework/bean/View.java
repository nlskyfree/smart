package com.smart.framework.bean;

import java.util.Map;

/**
 *
 * @project Writing Java web frame from zero
 * @package com.smart.framework.bean
 * @file View.java
 * @time 2015-12-30下午07:40:17
 * @description 
 * @author   nilu
 * @version 1.0
 *
 */
public class View {
    private String path;
    private Map<String, Object> model;
    
    public View(String path, Map<String, Object> model) {
        this.path = path;
        this.model = model;
    }
    
    /**
     * @time2015-12-30下午07:41:21
     * get
     * @return the path
     */
    public String getPath() {
        return path;
    }
    /**
     * @time 2015-12-30下午07:41:21
     * set
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }
    /**
     * @time2015-12-30下午07:41:21
     * get
     * @return the model
     */
    public Map<String, Object> getModel() {
        return model;
    }
    /**
     * @time 2015-12-30下午07:41:21
     * set
     * @param model the model to set
     */
    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
    
    
}
