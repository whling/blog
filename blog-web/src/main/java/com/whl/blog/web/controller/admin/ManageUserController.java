package com.whl.blog.web.controller.admin;

import com.xingfly.constant.AttributeConstant;
import com.xingfly.model.User;
import com.xingfly.model.dto.UserDto;
import com.xingfly.service.UserService;
import com.xingfly.util.MyMD5;
import com.xingfly.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by SuperS on 16/3/1.
 * 管理用户 编辑页面
 */
@Controller
@RequestMapping("manage/user")
public class ManageUserController {
    @Autowired
    private UserService userService;

    //显示用户列表
    @RequestMapping(method = RequestMethod.GET)
    public String showUsers(ModelMap model, HttpSession session) {
        model.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
        model.addAttribute(AttributeConstant.MAIN_PAGE, "admin/user/editor.vm");
        model.addAttribute(AttributeConstant.USERS, userService.getUsers());
        return "admin/index";
    }

    //更新用户
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createAction(User user, ModelMap model, HttpSession session) {
        model.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
        model.addAttribute(AttributeConstant.MAIN_PAGE, "admin/user/editor.vm");
        if (StringUtil.isNotEmpty(user.getUsername()) && StringUtil.isNotEmpty(user.getPassword()) && StringUtil.isNotEmpty(user.getNickname()) && StringUtil.isNotEmpty(user.getEmail())) {
            if (userService.userIsNotEmpty(user.getUsername())) {
                user.setPassword(MyMD5.MD5(user.getPassword()));
                user.setImagePath("/images/users/tx.jpg");
                userService.saveUser(user);
                model.addAttribute(AttributeConstant.RETURN_INFO, "用户添加成功!");
            } else {
                model.addAttribute(AttributeConstant.ERROR, "用户已存在!");
            }
        } else {
            model.addAttribute(AttributeConstant.ERROR, "有未填写用户信息!");
        }
        model.addAttribute(AttributeConstant.USERS, userService.getUsers());
        return "admin/index";
    }

    //根据ID显示更新页面
    @RequestMapping(value = "update/{userId:[0-9]+}", method = RequestMethod.GET)
    public String showUpdate(@PathVariable("userId") Integer userId, ModelMap model, HttpSession session) {
        model.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
        model.addAttribute(AttributeConstant.MAIN_PAGE, "admin/user/editor.vm");
        User user = userService.getUser(userId);
        if (StringUtil.isNotEmpty(user.getUsername())) {
            model.addAttribute("editoruser", user);
        } else {
            model.addAttribute(AttributeConstant.ERROR, "用户修改失败!");
        }
        model.addAttribute(AttributeConstant.USERS, userService.getUsers());
        return "admin/index";
    }

    //更用户操作
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateAction(User user, ModelMap model, HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER);
        model.addAttribute(AttributeConstant.USER, userDto);
        model.addAttribute(AttributeConstant.MAIN_PAGE, "admin/user/editor.vm");
        if (StringUtil.isNotEmpty(user.getUsername()) && StringUtil.isNotEmpty(user.getPassword()) && StringUtil.isNotEmpty(user.getNickname()) && StringUtil.isNotEmpty(user.getEmail())) {
            //如果管理员没有编辑选择用户的密码,就不在对密码进行MD5加密
            if (!user.getPassword().equals(userService.getUser(user.getId()).getPassword())) {
                user.setPassword(MyMD5.MD5(user.getPassword()));
            }
            userService.updateUser(user);
            model.addAttribute(AttributeConstant.RETURN_INFO, "修改用户成功!");
            //判断更新用户 是否为 当前登陆用户 如果是 需要更新当前登陆用户的 显示信息.
            if (user.getId() == userDto.getId()) {
                UserDto currentUser = userService.login(user);
                session.setAttribute(AttributeConstant.CURRENT_USER, currentUser);
                model.addAttribute(AttributeConstant.USER, currentUser);
            }
        } else {
            model.addAttribute(AttributeConstant.ERROR, "有未填写用户信息!");
        }
        model.addAttribute(AttributeConstant.USERS, userService.getUsers());
        return "admin/index";
    }

    //根据ID删除用户
    @RequestMapping(value = "delete/{userId:[0-9]+}")
    public String deleteAction(ModelMap model, HttpSession session, @PathVariable("userId") Integer userId) {
        model.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
        model.addAttribute(AttributeConstant.MAIN_PAGE, "admin/user/editor.vm");
        User user = userService.getUser(userId);
        if (StringUtil.isNotEmpty(user.getUsername())) {
            userService.deleteUser(userId);
            model.addAttribute(AttributeConstant.RETURN_INFO, "删除用户成功!");
        } else {
            model.addAttribute(AttributeConstant.ERROR, "找不到该用户");
        }
        model.addAttribute(AttributeConstant.USERS, userService.getUsers());
        return "admin/index";
    }
}
