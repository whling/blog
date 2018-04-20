package com.whl.blog.web.service;


import com.whl.blog.api.UserService;
import com.whl.blog.api.dto.UserDto;
import com.whl.blog.api.pojo.User;
import com.whl.blog.web.common.pager.mysql.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by SuperS on 15/12/13.
 */
@Service("userServiceImpl")
public class UserServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserService userService;

    public boolean userIsNotEmpty(String name) {
        return userService.userIsNotEmpty(name);
    }

    public List<User> getPageUsers(Pager pager) {
        return userService.getPageUsers(pager);
    }

    public UserDto login(User user) {
        return userService.login(user);
    }

    public void saveUser(User user) {
        userService.saveUser(user);
    }

    public void updateUser(User user) {
        userService.updateUser(user);
    }

    public User getUser(Integer id) {
        return userService.getUser(id);
    }

    public void deleteUser(Integer id) {
        userService.deleteUser(id);
    }

    public List<User> getUsers() {
        return userService.getUsers();
    }
}
