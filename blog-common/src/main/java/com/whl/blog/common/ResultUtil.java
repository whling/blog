package com.whl.blog.common;

import com.jiupai.platform.merchant.common.result.FailureResult;
import com.jiupai.platform.merchant.common.result.ReMessage;
import com.jiupai.platform.merchant.common.result.Result;
import com.jiupai.platform.merchant.common.result.SuccessResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by whling on 2017/6/4.
 *
 */
public class ResultUtil {

    public static Result defaultSuccess() {
        return new SuccessResult(
                new ReMessage(
                        BaseCode.SUCCESS.code,
                        BaseCode.SUCCESS.reMessage
                )
        );
    }

    public static Result defaultSuccess(Object retData) {
        return new SuccessResult(
                retData,
                new ReMessage(
                        BaseCode.SUCCESS.code,
                        BaseCode.SUCCESS.reMessage
                )
        );
    }

    public static Result defaultGridSuccess(int total, Object rows) {
        Map<String, Object> gridData = new HashMap<>();
        gridData.put("total", total);
        gridData.put("rows", rows);
        return new SuccessResult(
                gridData,
                new ReMessage(
                        BaseCode.SUCCESS.code,
                        BaseCode.SUCCESS.reMessage
                )
        );
    }

    public static Result defaultGridSuccess(int total, Object rows, Map<String, Object> exData) {
        Map<String, Object> gridData = new HashMap<>();
        gridData.put("total", total);
        gridData.put("rows", rows);
        gridData.put("ext", exData);
        return new SuccessResult(
                gridData,
                new ReMessage(
                        BaseCode.SUCCESS.code,
                        BaseCode.SUCCESS.reMessage
                )
        );
    }

    public static Result defaultGridFailure(String reMessage) {
        Map<String, Object> gridData = new HashMap<>();
        gridData.put("total", NumberUtils.INTEGER_ZERO);
        gridData.put("rows", new ArrayList<>());
        return new FailureResult(
                gridData,
                new ReMessage(
                        BaseCode.FAILURE.code,
                        reMessage
                )
        );
    }

    public static Result defaultFailure(String reMessage) {
        return new FailureResult(
                new ReMessage(
                        BaseCode.FAILURE.code,
                        reMessage
                )
        );
    }

    public static Result defaultFailure(String returnCode, String reMessage) {
        return new FailureResult(
                new ReMessage(
                        returnCode,
                        reMessage
                )
        );
    }

    public static boolean checkSuccess(Result result) {
        try {
            return StringUtils.equals(
                    result.getReMessage().getReturnCode(),
                    BaseCode.SUCCESS.code
            );
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean checkSuccessWithData(Result result) {
        try {
            return StringUtils.equals(
                    result.getReMessage().getReturnCode(),
                    BaseCode.SUCCESS.code
            ) && null != result.getData();
        } catch (Exception ex) {
            return false;
        }
    }

}
