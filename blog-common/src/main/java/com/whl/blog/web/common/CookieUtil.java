package com.whl.blog.web.common;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by whling on 2017/6/10.
 *
 */
public class CookieUtil {

    public interface Proxy {
        void proxy(Cookie cookie);
    }

    public static void addCookie(HttpServletResponse response, String name, String value) {
        addCookie(response, name, value, null);
    }

    public static void addCookie(HttpServletResponse response, String name, String value, Proxy proxy) {
        Cookie cookie = new Cookie(name, value);
        if (proxy != null) proxy.proxy(cookie);
        response.addCookie(cookie);
    }

    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies;
        if (null != (cookies = request.getCookies())) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equals(cookie.getName(), name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies;
        if (null != (cookies = request.getCookies())) {
            for (Cookie cookie: cookies) {
                if (StringUtils.equals(cookie.getName(), name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
