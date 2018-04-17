package com.whl.blog.common.handler;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.jiupai.platform.merchant.common.result.ReMessage;
import com.jiupai.platform.merchant.exception.MerchantWebException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常处理类
 * Created by liuweb on 2016/8/29.
 */
public class MerchantExceptionHandler implements HandlerExceptionResolver
{
    private static final Logger logger = LoggerFactory.getLogger(MerchantExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ex.printStackTrace();
        ModelAndView mv = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        Map<String,Object> maps = new HashMap<String,Object>();
        maps.put("error",new ReMessage("100", "系统错误"));
        if(ex instanceof MerchantWebException){
        	MerchantWebException bex = (MerchantWebException) ex;
        	String msg = bex.getMessage();
        	maps.put("error",new ReMessage("100", msg));
		}
        
        maps.put("data","");
        view.setAttributesMap(maps);
        mv.setView(view);
        return mv;
    }
}
