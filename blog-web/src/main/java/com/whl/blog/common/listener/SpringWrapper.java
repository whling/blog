package com.whl.blog.common.listener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by whling on 2017/6/7.
 *
 */
public class SpringWrapper implements ServletContextListener {

    private static WebApplicationContext springContext;

    public SpringWrapper() {
        super();
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        springContext = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

    public static ApplicationContext getApplicationContext() {
        return springContext;
    }


    public static <T> T getBean(Class<T> type) throws BeansException {
        return springContext.getBean(type);
    }

    public static Object getBean(String name) throws BeansException {
        return springContext.getBean(name);
    }

}
