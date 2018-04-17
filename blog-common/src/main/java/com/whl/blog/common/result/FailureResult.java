package com.whl.blog.common.result;

/**
 * Created by whling on 2017/6/4.
 *
 */
public class FailureResult<T> extends AbstractResult<T> {

    public FailureResult() {
    }

    public FailureResult(ReMessage reMessage) {
        super(reMessage);
    }

    public FailureResult(T data, ReMessage reMessage) {
        super(data, reMessage);
    }

}
