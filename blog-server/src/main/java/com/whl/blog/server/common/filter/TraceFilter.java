package com.whl.blog.server.common.filter;

import com.alibaba.dubbo.rpc.*;
import com.alibaba.fastjson.JSON;
import com.whl.blog.api.request.BaseParams;
import com.whl.blog.web.common.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by whling on 2018/4/19.
 */
public class TraceFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(TraceFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Object[] arguments = invocation.getArguments();
        String userId = "unknown", traceId = "unknown";
        for (Object argument : arguments) {
            if (argument instanceof BaseParams) {
                userId = ((BaseParams) argument).getUserId();
                traceId = ((BaseParams) argument).getTraceID();
            }
        }
        long startTime = System.currentTimeMillis();
        String service = invoker.getInterface().getName() + "." + invocation.getMethodName();
        logger.info("发起时间 [{}] 发起用户 [{}] traceId [{}] 消费方 [{}] 调用接口 [{}] 请求报文 [{}]",
                DateUtil.getCurDateTime(), userId, traceId, RpcContext.getContext().getRemoteAddressString(), service, JSON.toJSONString(invocation.getArguments()));

        Result result = invoker.invoke(invocation);
        logger.info("发起时间 [{}] 发起用户 [{}] traceId [{}] 消费方 [{}] 调用接口 [{}] 返回报文 [{}] 耗时 [{}] 毫秒",
                DateUtil.getCurDateTime(), userId, traceId, RpcContext.getContext().getRemoteAddressString(), service, JSON.toJSONString(result.getValue()), (System.currentTimeMillis() - startTime));
        return result;
    }

}
