package com.whl.blog.web.common;

import com.jiupai.platform.merchant.cache.MerCache;
import com.jiupai.platform.merchant.pojo.request.BaseParams;

/**
 * Created by whling on 2017/7/3.
 */
public class BaseParamsUtil {

    public static BaseParams getBaseParams() {
        BaseParams baseParams = new BaseParams();
        baseParams.setMerId(SessionHolder.getValue(MerCache.MER_ID));
        baseParams.setMerSts(SessionHolder.getValue(MerCache.MER_STS));
        baseParams.setMerProv(SessionHolder.getValue(MerCache.MER_PROV));
        baseParams.setMerCnm(SessionHolder.getValue(MerCache.MER_CNM));
        baseParams.setMerAcNo(SessionHolder.getValue(MerCache.MER_AC_NO));
        baseParams.setMerUsrNo(SessionHolder.getValue(MerCache.MER_USR_NO));

        baseParams.setOprId(SessionHolder.getValue(MerCache.MER_OPR_ID));
        baseParams.setOprLogNm(SessionHolder.getValue(MerCache.MER_OPR_NM));
        baseParams.setOprLogId(SessionHolder.getValue(MerCache.MER_OPR_LOG_ID));
        baseParams.setOprHeadFlg(SessionHolder.getValue(MerCache.MER_OPR_HEAD_FLG));

        baseParams.setClientIP(SessionHolder.getValue(MerCache.MER_REQUEST_IP));
        baseParams.setTraceID(SessionHolder.getValue(MerCache.MER_REQUEST_ID));
        return baseParams;
    }

}
