package com.whl.blog.api;

import com.whl.blog.api.pojo.User;
import com.whl.blog.api.request.BaseParams;
import com.whl.blog.web.common.result.Result;

/**
 * Created by whling on 2018/4/18.
 */
public interface IUserService {

    Result<User> queryUserInfo(BaseParams baseParams);
}
