package com.whl.blog.common.interceptor;

import com.jiupai.platform.merchant.cache.MerCache;
import com.jiupai.platform.merchant.common.*;
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
 * Created by whling on 2017/6/8.
 *
 */
public class CookieAuthInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(CookieAuthInterceptor.class);

    private static final int sitPathLen = MerchantContext.SIT_PATH.length();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean reBoolean = false;
        try {
            String[] cookieValues = CookieAuthUtil.getCookieAuthId(request);
            if (ArrayUtils.isNotEmpty(cookieValues)) {
                if (reBoolean = CookieAuthUtil.checkAuth(IPUtil.getRemoteIP(request), cookieValues)) {
                    Map<String, String> cacheMap = MerCache.getCacheMap(cookieValues[0]);
                    cacheMap.put(MerCache.MER_REQUEST_ID, MDC.get(Constants.MDC_REQ_ID));
                    cacheMap.put(MerCache.MER_REQUEST_URL, request.getRequestURI().substring(sitPathLen));
                    SessionHolder.setMap(cacheMap);
                }
            } else {
                proccessByRequestType(request, response, BaseCode.LOGINOUT);
                return reBoolean;
            }
        } catch (Exception ex) {
            logger.warn("cookie auth warn: ", ex);
        }
        if (!reBoolean) {
            proccessByRequestType(request, response, BaseCode.TIMEOUT);
        }
        return reBoolean;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        SessionHolder.clear();
    }

    private static final String exitScriptTmp =
            "<script type=\"text/javascript\">" +
                "window.location.href=\"" + MerchantContext.SIT_PATH + "\";" +
            "</script>";

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

}
