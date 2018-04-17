package com.whl.blog.common.result;

/**
 * Created by whling on 2017/6/4.
 *
 */
public class SuccessResult<T> extends AbstractResult<T> {

    public SuccessResult() {
        super();
    }

    public SuccessResult(ReMessage reMessage) {
        super(reMessage);
    }

    public SuccessResult(T data, ReMessage reMessage) {
        super(data, reMessage);
    }

}
