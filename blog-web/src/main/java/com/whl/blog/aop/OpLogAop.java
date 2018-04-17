package com.whl.blog.aop;

import com.whl.blog.common.BaseCode;
import com.whl.blog.common.SessionHolder;
import com.whl.blog.common.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pasta on 2017/6/25.
 */
public class OpLogAop {

    private static final Logger logger = LoggerFactory.getLogger(OpLogAop.class);

    public void afterReturning(Object ret) {
        try {

            if (!(ret instanceof Result)) {
                return;
            }

            String merId = SessionHolder.getValue(MerCache.MER_ID);
            String oprId = SessionHolder.getValue(MerCache.MER_OPR_LOG_ID);
            String oprIp = SessionHolder.getValue(MerCache.MER_REQUEST_IP);
            String opUri = SessionHolder.getValue(MerCache.MER_REQUEST_URL);
            String oprTyp = SessionHolder.getValue(MerCache.MER_OPR_HEAD_FLG);
            if (SysMenuCache.checkHasFuncUri(opUri)) {
                PlatFunc platFunc = SysMenuCache.queryFuncByFuncUri(opUri);
                if (platFunc.getLogSts() == EPubSts.SUCC.key) {
                    HisOpLaundry hisOpLaundry = new HisOpLaundry();
                    hisOpLaundry.setMerId(merId);
                    hisOpLaundry.setOprId(oprId);
                    hisOpLaundry.setOprIp(oprIp);
                    hisOpLaundry.setFuncId(platFunc.getFuncId());
                    hisOpLaundry.setOprTyp(Integer.parseInt(oprTyp));

                    String returnCode = ((Result) ret).getReMessage().getReturnCode();
                    String returnMsg = ((Result) ret).getReMessage().getReturnMessage();
                    hisOpLaundry.setOpSts(
                            returnCode.equals(BaseCode.SUCCESS.code) ?
                                    EPubSts.SUCC.key : EPubSts.FAIL.key
                    );
                    hisOpLaundry.setOpRet(returnMsg);
                    OpMonitor.notifyOpEvent(hisOpLaundry);
                }
            }
        } catch (Exception ex) {
            logger.error("occur exception: ", ex);
        }
    }

}
