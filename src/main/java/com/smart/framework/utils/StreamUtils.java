package com.smart.framework.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @project Writing Java web frame from zero
 * @package com.smart.framework.utils
 * @file StreamUtils.java
 * @time 2015-12-29下午08:03:58
 * @description 
 * @author   nilu
 * @version 1.0
 *
 */
public class StreamUtils {
    private final static Logger LOGGER = LoggerFactory.getLogger(StreamUtils.class);
    
    /**
     * @description read inputstream
     * @time 2015-12-29下午08:11:30
     * @param in
     * @return
     * @author   nilu
     */
    public static String getString(InputStream in) {
        final BufferedReader br = new BufferedReader(new InputStreamReader(in));
        final StringBuilder sb = new StringBuilder();
        try {
            String line = null;
            while(null != (line = br.readLine())) {
                sb.append(line);
            }
        } catch(Exception e) {
            LOGGER.error("read HttpServletRequest inputstream failure", e);
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
