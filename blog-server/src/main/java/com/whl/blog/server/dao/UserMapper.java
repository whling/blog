package com.whl.blog.server.dao;

import com.whl.blog.api.dto.UserDto;
import com.whl.blog.api.pojo.User;
import com.whl.blog.web.common.pager.mysql.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SuperS on 16/3/8.
 */
@Repository
public interface UserMapper {
    // 用户登录
    UserDto login(@Param("username") String username,
                  @Param("password") String password);

    // 添加或保存用户
    void save(User user);

    // 更新用户
    void update(User user);

    // 获取用户列表
    List<User> getUsers();

    // 删除用户
    void delete(Integer id);

    // 获取用户
    User getUser(Integer id);

    // 分页获取用户
    List<User> pagenation(Pager pager);

    // 是否存在用户
    int userIsNotEmpty(String name);
}
