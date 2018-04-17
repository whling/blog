package com.whl.blog.server.service;

import com.whl.blog.api.IUserService;
import com.whl.blog.api.pojo.User;
import com.whl.blog.api.request.BaseParams;
import com.whl.blog.server.dao.UserMapper;
import com.whl.blog.web.common.BaseCode;
import com.whl.blog.web.common.ResultUtil;
import com.whl.blog.web.common.result.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by whling on 2018/4/18.
 */
@Service
public class UserService implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Result<User> queryUserInfo(BaseParams baseParams) {
        User user = userMapper.queryUserInfo(baseParams.getUserName());
        if (null == user) {
            return ResultUtil.defaultFailure(BaseCode.UDNE.code, BaseCode.UDNE.reMessage);
        }
        return ResultUtil.defaultSuccess(user);
    }
}
