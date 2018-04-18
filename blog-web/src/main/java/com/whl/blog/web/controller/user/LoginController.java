package com.whl.blog.web.controller.user;

import com.xingfly.constant.AttributeConstant;
import com.xingfly.model.User;
import com.xingfly.model.dto.UserDto;
import com.xingfly.service.UserService;
import com.xingfly.util.MyMD5;
import com.xingfly.util.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by SuperS on 16/2/29.
 * 登录操作
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    //显示登录页面
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin(HttpServletRequest request, ModelMap model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            String username = "";
            String password = "";
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                    model.addAttribute("username", username);
                }
                if ("password".equals(cookie.getName())) {
                    password = cookie.getValue();
                    model.addAttribute("password", password);
                }
            }
        }
        return "user/login/login";
    }

    //登陆操作
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, @RequestParam(defaultValue = "no") String remember, String code, ModelMap model, HttpSession session, HttpServletResponse response) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(MyMD5.MD5(password));
        UserDto currentUser = userService.login(user);
        if (code.toUpperCase().equals((String) session.getAttribute("code"))) {
            if (currentUser != null) {
                if (remember.equals("yes")) {
                    //记住我操作
                    rememberMe(username, password, response);
                }
                session.setAttribute(AttributeConstant.CURRENT_USER, currentUser);
                //跳转
                return "redirect:/manage";
            } else {
                model.addAttribute("error", "用户名或密码错误");
            }
        } else {
            model.addAttribute("error", "验证码错误");
        }
        return "user/login/login";
    }

    //退出操作
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute(AttributeConstant.CURRENT_USER, null);
        return "redirect:/";
    }

    //验证码显示
    @RequestMapping(value = "/login/codeimg", method = RequestMethod.GET)
    public void showCode(HttpServletResponse response, HttpSession session) {
        VerificationCode verificationCode = new VerificationCode();
        String code = verificationCode.RandomString(5);
        session.setAttribute("code", code);
        verificationCode.CreateImg(100, 33, code, 80);
        try {
            verificationCode.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试
    @RequestMapping(value = "/login/test")
    public String testLogin(HttpSession session) {
        User user = new User();
        user.setUsername("admin");
        user.setPassword(MyMD5.MD5("admin"));
        UserDto currentUser = userService.login(user);
        session.setAttribute(AttributeConstant.CURRENT_USER, currentUser);
        return "redirect:/manage";
    }

    private void rememberMe(String username, String password,
                            HttpServletResponse response) {
        Cookie cookieUserName = new Cookie("username", username);
        Cookie cookiePassWord = new Cookie("password", password);
        cookiePassWord.setMaxAge(AttributeConstant.DAY_TIME * 7);
        cookieUserName.setMaxAge(AttributeConstant.DAY_TIME * 7);
        response.addCookie(cookiePassWord);
        response.addCookie(cookieUserName);
    }

}
