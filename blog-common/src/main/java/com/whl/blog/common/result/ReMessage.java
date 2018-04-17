package com.whl.blog.common.result;

import java.io.Serializable;

/**
 * Created by whling on 2017/6/4.
 *
 */
public class ReMessage implements Serializable {

    private String returnCode;

    private String returnMessage;

    public ReMessage(String returnCode, String returnMessage) {
        this.returnCode = returnCode;
        this.returnMessage= returnMessage;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

}
