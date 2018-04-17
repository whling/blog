package com.whl.blog.server.dao;

import com.whl.blog.api.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * Created by whling on 2018/4/18.
 */
public interface UserMapper {

    User queryUserInfo(String userName);
}
