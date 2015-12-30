package com.smart.framework.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @project Writing Java web frame from zero
 * @package com.smart.framework.utils
 * @file CodecUtils.java
 * @time 2015-12-29下午07:57:01
 * @description 
 * @author   nilu
 * @version 1.0
 *
 */
public class CodecUtils {
    
    private static String charset = "UTF-8";
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);
    
    /**
     * @description encode url
     * @time 2015-12-29下午08:03:35
     * @param url
     * @return
     * @author   nilu
     */
    public static String encodeURL(String url) {
        try {
            return URLEncoder.encode(url, charset);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("encode url failure", e);
            throw new RuntimeException(e);
        }
    }
    
    /**
     * @description decode url
     * @time 2015-12-29下午08:03:42
     * @param url
     * @return
     * @author   nilu
     */
    public static String decodeURL(String url) {
        try {
            return URLDecoder.decode(url, charset);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("decode url failure", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * @time2015-12-29下午08:02:11
     *get
     * @return the charset
     */
    public static String getCharset() {
        return charset;
    }

    /**
     * @time 2015-12-29下午08:02:11
     * set
     * @param charset the charset to set
     */
    public static void setCharset(String charset) {
        CodecUtils.charset = charset;
    }
}
