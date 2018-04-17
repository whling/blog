package com.whl.blog.common.interceptor;

import com.whl.blog.common.Constants;
import com.whl.blog.common.CookieAuthUtil;
import com.whl.blog.common.UUIDUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by whling on 2018/4/17.
 */
public class TraceInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TraceInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        MDC.put(Constants.MDC_START_TIME, String.valueOf(System.currentTimeMillis()));
        MDC.put(Constants.MDC_URI, request.getRequestURI());
        MDC.put(Constants.MDC_REQ_ID, UUIDUtil.genUUID());
        if (!request.getRequestURI().endsWith("login/in")) {
            String[] cookieValues;
            String oprLogId = "UNKNOW";
            if (null != (cookieValues = CookieAuthUtil.getCookieAuthId(request))) {
                if (ArrayUtils.isNotEmpty(cookieValues)) {
                    oprLogId = cookieValues[1];
                }
            }
            MDC.put(Constants.MDC_USR_OPR_LOG_ID, oprLogId);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info("request {} cost time {} ms", MDC.get(Constants.MDC_URI), (System.currentTimeMillis() - NumberUtils.toLong(MDC.get(Constants.MDC_START_TIME))));

        MDC.remove(Constants.MDC_URI);
        MDC.remove(Constants.MDC_REQ_ID);
        MDC.remove(Constants.MDC_START_TIME);
        MDC.remove(Constants.MDC_USR_OPR_LOG_ID);
    }
}
