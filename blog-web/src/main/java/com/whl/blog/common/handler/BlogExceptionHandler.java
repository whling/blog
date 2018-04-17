package com.whl.blog.common.handler;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.whl.blog.common.result.ReMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by whling on 2016/8/29.
 * 统一异常处理类
 */
public class BlogExceptionHandler implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(BlogExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ex.printStackTrace();
        ModelAndView mv = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("error", new ReMessage("100", "系统错误"));
        //if (ex instanceof MerchantWebException) {
        //    MerchantWebException bex = (MerchantWebException) ex;
        //    String msg = bex.getMessage();
        //    maps.put("error", new ReMessage("100", msg));
        //}

        maps.put("data", "");
        view.setAttributesMap(maps);
        mv.setView(view);
        return mv;
    }
}
