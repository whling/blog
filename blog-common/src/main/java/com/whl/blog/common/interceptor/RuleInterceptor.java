package com.whl.blog.common.interceptor;

import com.jiupai.platform.merchant.cache.MerCache;
import com.jiupai.platform.merchant.common.BaseCode;
import com.jiupai.platform.merchant.common.Constants;
import com.jiupai.platform.merchant.common.ResultUtil;
import com.jiupai.platform.merchant.common.SessionHolder;
import com.jiupai.platform.merchant.common.result.Result;
import com.jiupai.platform.merchant.service.RuleMgrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by whling on 2017/6/18.
 *
 */
public class RuleInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RuleInterceptor.class);

    @Resource
    private RuleMgrService ruleMgrService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String reqUri = SessionHolder.getValue(MerCache.MER_REQUEST_URL);
        Result<Boolean> ruleCheckRet = ruleMgrService.checkRule(reqUri);
        if (ResultUtil.checkSuccessWithData(ruleCheckRet) && !ruleCheckRet.getData()) {
            proccessByRequestType(request, response, BaseCode.UNAUTHORIZED);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private static final String exitScriptTmp =
            "<script type=\"text/javascript\">" +
                "Messenger().post('访问受限！');" +
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
