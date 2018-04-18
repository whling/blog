package com.whl.blog.server.service;


import com.whl.blog.api.UserService;
import com.whl.blog.api.dto.UserDto;
import com.whl.blog.api.pojo.User;
import com.whl.blog.server.dao.UserMapper;
import com.whl.blog.web.common.pager.mysql.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by SuperS on 15/12/13.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public boolean userIsNotEmpty(String name) {
        int total = 0;
        try {
            total = userMapper.userIsNotEmpty(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total > 0 ? true : false;
    }

    @Override
    public List<User> getPageUsers(Pager pager) {
        List<User> users = null;
        try {
            users = userMapper.pagenation(pager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public UserDto login(User user) {
        UserDto userDto = null;
        try {
            userDto = userMapper.login(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDto;
    }

    @Override
    public void saveUser(User user) {
        try {
            userMapper.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            userMapper.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(Integer id) {
        User u = null;
        try {
            u = userMapper.getUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public void deleteUser(Integer id) {
        try {
            userMapper.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUsers() {
        List<User> users = null;
        try {
            users = userMapper.getUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
}
