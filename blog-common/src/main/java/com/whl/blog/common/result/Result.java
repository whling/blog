package com.whl.blog.common.result;

import java.io.Serializable;

/**
 * Created by whling on 2017/6/4.
 */
public interface Result<T> extends Serializable {

    public void setData(T data);

    public T getData();

    public void setReMessage(ReMessage reMessage);

    public ReMessage getReMessage();

}
