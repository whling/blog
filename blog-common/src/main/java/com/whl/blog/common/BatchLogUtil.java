package com.whl.blog.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 批量代收付日志
 * @author think
 */
public class BatchLogUtil {
    private static final Logger logger = LoggerFactory.getLogger(BatchLogUtil.class);

    public static void info(String msg){
    	logger.info(msg);
    }

    public static void info(String msg, Object... params) {
    	logger.error(String.format(msg, params));
    }
    
    public static void error(String msg){
    	logger.error(msg);
    }
    
    public static void error(String msg, Object... params) {
    	logger.error(String.format(msg, params));
    }
}
