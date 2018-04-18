package com.whl.blog.web.controller.admin;

import com.xingfly.constant.AttributeConstant;
import com.xingfly.model.dto.UserDto;
import com.xingfly.model.dto.WebAppDto;
import com.xingfly.service.WebAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by SuperS on 16/2/29.
 * 显示Manage主页
 */
@Controller
@RequestMapping("/manage")
public class ManageController {
    @Autowired
    private WebAppService webAppService;

    @RequestMapping(method = RequestMethod.GET)
    public String manageHome(ModelMap model, HttpSession session) {
        WebAppDto webAppDto = webAppService.getWebDtoWebApp(webAppService.getWebAppDtos().get(0).getId());
        webAppDto.setArticleViews(webAppService.getArticlesView());
        model.addAttribute(AttributeConstant.MAIN_PAGE, "admin/home/home.vm");
        model.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
        model.addAttribute(AttributeConstant.WEB_APP_DTO, webAppDto);

        return "admin/index";
    }
}
