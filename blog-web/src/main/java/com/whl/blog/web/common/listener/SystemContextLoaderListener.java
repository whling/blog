package com.whl.blog.web.common.listener;

import com.whl.blog.web.common.BlogContext;
import com.whl.blog.web.common.IPUtil;
import com.whl.blog.web.common.PPSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

/**
 * Created by whling on 2017/6/7.
 *
 */
public class SystemContextLoaderListener extends ContextLoaderListener {

    private static final Logger logger = LoggerFactory.getLogger(SystemContextLoaderListener.class);

    private static final String[] RESOURCES = {"environment", "cache"};

    static {
        //设置dubbo使用slf4j来记录日志
        System.setProperty("dubbo.application.logger","slf4j");
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            logger.info("blog-web get local IP {}", IPUtil.getLocalIP());
            BlogContext.initMerchantContext(new PPSUtil(RESOURCES));
        } catch (Exception ex) {
            logger.error("loss important config, load properties exception：", ex);
            throw new RuntimeException(ex);
        }
        super.contextInitialized(event);
    }
}
