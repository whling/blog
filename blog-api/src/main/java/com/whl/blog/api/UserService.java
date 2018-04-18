package com.whl.blog.api;


import com.whl.blog.api.dto.UserDto;
import com.whl.blog.api.pojo.User;
import com.whl.blog.web.common.pager.mysql.Pager;

import java.util.List;

/**
 * Created by SuperS on 15/12/9.
 */
public interface UserService {

    //登录
    UserDto login(User user);

    //保存用户 或者 添加用户
    void saveUser(User user);

    //更新用户
    void updateUser(User user);

    //删除用户
    void deleteUser(Integer id);

    //获取用户列表
    List<User> getUsers();

    //获取用户
    User getUser(Integer id);

    //分页获取用户
    List<User> getPageUsers(Pager pager);

    //是否存在用户
    boolean userIsNotEmpty(String name);
}
