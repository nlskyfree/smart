package com.smart.framework.utils;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @project Writing Java web frame from zero
 * @package com.smart.framework.utils
 * @file JsonUtils.java
 * @time 2015-12-29下午07:42:18
 * @description 
 * @author   nilu
 * @version 1.0
 *
 */
public final class JsonUtils {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);
    
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * @description transfer object to jsonstring
     * @time 2015-12-29下午07:55:19
     * @param <T>
     * @param value
     * @return
     * @author   nilu
     */
    public static <T> String objectToJsonString(T value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (Exception e) {
            LOGGER.error(value + " transfer to json failure", e);
            throw new RuntimeException(e);
        } 
    }
    
    /**
     * @description transfer jsonstring to object
     * @time 2015-12-29下午07:56:19
     * @param <T>
     * @param jsonString
     * @param clazz
     * @return
     * @author   nilu
     */
    public static <T> T jsonStringToObject(String jsonString, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (Exception e) {
            LOGGER.error(jsonString + " transfer to " + clazz.getName() + " failure", e);
            throw new RuntimeException(e);
        } 
    }
}
