package com.whl.blog.web.controller;

import com.whl.blog.api.WebAppService;
import com.whl.blog.api.pojo.WebApp;
import com.whl.blog.web.common.Constants;
import com.whl.blog.web.common.pager.mysql.Pager;
import com.whl.blog.web.service.ArticleServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by SuperS on 16/2/26.
 * 网站首页 负责显示文章列表 分页数为4
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private WebAppService webAppService;

    //显示首页 分页文章列表
    @RequestMapping(method = RequestMethod.GET)
    public String home(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex, ModelMap model, HttpServletRequest request) {
        String link = "index";
        if (webAppService.getWebAppDtos().size() == 0) {
            link = "redirect:/init";
        } else {
            Pager pager = new Pager(pageIndex, webAppService.getWebDtoWebApp(webAppService.getWebAppDtos().get(0).getId()).getUserPageArticleSize(), articleService.count());
            model.addAttribute(Constants.MAIN_PAGE, "user/article/articlelist.vm");
            model.addAttribute(Constants.WEB_APP_DTO, webAppService.getWebDtoWebApp(webAppService.getWebAppDtos().get(0).getId()));
            model.addAttribute(Constants.ARTICLES, articleService.getPageArticles(pager));
            model.addAttribute(Constants.PAGER, pager);
        }
        return link;
    }

    @RequestMapping(value = "init", method = RequestMethod.GET)
    public String init(ModelMap model) {
        return "admin/init/init";
    }

    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String initAction(ModelMap model, WebApp webApp) {
        String link = "redirect:/init";
        if (StringUtils.isNotEmpty(webApp.getWebName()) && StringUtils.isNotEmpty(webApp.getWebTitle())) {
            if (StringUtils.isNotEmpty(webApp.getAdminPageArticleSize().toString()) && StringUtils.isNotEmpty(webApp.getUserPageArticleSize().toString())) {
                webAppService.saveWebApp(webApp);
                link = "redirect:/";
            }
        }
        return link;
    }
}