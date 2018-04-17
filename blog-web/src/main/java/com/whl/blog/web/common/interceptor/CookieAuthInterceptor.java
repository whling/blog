package com.whl.blog.web.common.interceptor;

import com.whl.blog.web.cache.BlogCache;
import com.whl.blog.web.common.*;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by whling on 2018/4/17.
 */
public class CookieAuthInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(CookieAuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        boolean reBoolean = true;
        try {
            String[] cookieValues = CookieAuthUtil.getCookieAuthId(request);
            if (ArrayUtils.isNotEmpty(cookieValues)) {
                if (reBoolean = CookieAuthUtil.checkAuth(IPUtil.getRemoteIP(request), cookieValues)) {
                    Map<String, String> cacheMap = BlogCache.getCacheMap(cookieValues[0]);
                    cacheMap.put(BlogCache.BLOG_REQUEST_ID, MDC.get(Constants.MDC_REQ_ID));
                    cacheMap.put(BlogCache.BLOG_REQUEST_URL, request.getRequestURI().substring(1));
                    SessionHolder.setMap(cacheMap);
                }
            }
            //else {
            //    proccessByRequestType(request, response, BaseCode.LOGINOUT);
            //    return reBoolean;
            //}
        } catch (Exception ex) {
            logger.warn("cookie auth warn: ", ex);
        }
        //if (!reBoolean) {
        //    proccessByRequestType(request, response, BaseCode.TIMEOUT);
        //}
        return reBoolean;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private static void proccessByRequestType(HttpServletRequest request, HttpServletResponse response, BaseCode resCode) throws Exception {
        if (request.getHeader("x-requested-with") != null &&
                request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            response.setHeader(Constants.HEADER_RESPONSE_CODE, resCode.code);
            return;
        }
        response.setContentType("text/html;UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(exitScriptTmp);
        printWriter.flush();
        printWriter.close();
        return;
    }

    private static final String exitScriptTmp =
            "<script type=\"text/javascript\">" +
                    "window.location.href=\"" + BlogContext.SIT_PATH + "\";" +
                    "</script>";
}
