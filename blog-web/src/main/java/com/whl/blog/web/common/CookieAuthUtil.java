package com.whl.blog.web.common;

import com.whl.blog.web.cache.BlogCache;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by whling on 2017/6/11.
 *
 */
public class CookieAuthUtil {

    private static final Logger logger = LoggerFactory.getLogger(CookieAuthUtil.class);

    private static final String SEPARATOR   = "|";

    private static final String _JSESSIONID = "_JSESSIONID";

    private static final String AUTH_KEY    = "S%?3*`7v6005U2s%h!By5H=O4M6J^tp7";

    //key|oprLogId
    public static void addCookieAuthInfo(HttpServletResponse response, String oprLogId, String sessionToken) {
        String _jSessionId =  sessionToken + SEPARATOR +oprLogId;
        CookieUtil.addCookie(response, _JSESSIONID, AESedeUtil.encrypt(_jSessionId, AUTH_KEY), new CookieUtil.Proxy() {
            @Override
            public void proxy(Cookie cookie) {
                //setCookieParams(cookie);
            }
        });
    }

    public static String[] getCookieAuthId(HttpServletRequest request) {
        try {
            String _jSessionId;
            if (StringUtils.isNotBlank(_jSessionId = CookieUtil.getCookieValue(request, _JSESSIONID))) {
                String[] cookieValues = StringUtils.split(AESedeUtil.decrypt(_jSessionId, AUTH_KEY), SEPARATOR);
                return ArrayUtils.isEmpty(cookieValues) ? null : (cookieValues.length == 2 ? cookieValues : null);
            }
        } catch (Exception ex) {
            logger.warn("get client cookie auth id warn: ", ex);
        }
        return null;
    }

    public static boolean checkAuth(String requestIp, String[] cookieValues) {
        String sessionToken = cookieValues[0], oprLogId = cookieValues[1];
        if (BlogCache.isTimeout(sessionToken)) {
            logger.info("checkAuth fail [session time out!]");
            return false;
        }
        String loginIp;
        if (!StringUtils.equals((loginIp = BlogCache.getValue(sessionToken, BlogCache.BLOG_REQUEST_IP)), requestIp)) {
            logger.info("checkAuth fail loginIp [{}] reqIp [{}]", loginIp, requestIp);
            return false;
        }
        String loginOprId;
        if (!StringUtils.equals((loginOprId = BlogCache.getValue(sessionToken, BlogCache.BLOG_OPR_LOG_ID)), oprLogId)) {
            logger.info("checkAuth fail loginOprId [{}] reqOprId [{}]", loginOprId, oprLogId);
            return false;
        }
        return true;
    }

    //private static void setCookieParams(Cookie cookie) {
    //    cookie.setDomain(BlogContext.SIT_DOMAIN);
    //    cookie.setMaxAge(BlogContext.SIT_MAXAGE);
    //    cookie.setPath(BlogContext.SIT_PATH);
    //}

}
