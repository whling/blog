package com.whl.blog.common.result;

/**
 * Created by whling on 2017/6/4.
 *
 */
public abstract class AbstractResult<T> implements Result<T> {

    private T data;

    private ReMessage reMessage;

    public AbstractResult() {
    }

    public AbstractResult(ReMessage reMessage) {
        this.reMessage = reMessage;
    }

    public AbstractResult(T data, ReMessage reMessage) {
        this.data = data;
        this.reMessage = reMessage;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setReMessage(ReMessage reMessage) {
        this.reMessage = reMessage;
    }

    @Override
    public ReMessage getReMessage() {
        return reMessage;
    }
}
