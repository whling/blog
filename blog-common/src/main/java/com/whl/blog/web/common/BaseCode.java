package com.whl.blog.web.common;

/**
 * Created by whling on 2017/6/4.
 *
 */
public enum BaseCode {

    UDNE("444", "用户不存在！"),
    FAILURE("500", "处理失败！"),
    SUCCESS("200", "处理成功！"),
    TIMEOUT("504", "session超时！"),
    LOGINOUT("505", "登出系统！"),
    UNAUTHORIZED("401", "没有权限访问该资源");


    public String code;
    public String reMessage;

    BaseCode(String code, String reMessage) {
        this.code = code;
        this.reMessage = reMessage;
    }
}
