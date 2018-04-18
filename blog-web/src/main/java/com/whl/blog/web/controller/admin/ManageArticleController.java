package com.whl.blog.web.controller.admin;

import com.xingfly.constant.AttributeConstant;
import com.xingfly.model.Article;
import com.xingfly.model.dto.ArticleDto;
import com.xingfly.model.dto.UserDto;
import com.xingfly.service.ArticleService;
import com.xingfly.service.CategoryService;
import com.xingfly.service.WebAppService;
import com.xingfly.util.Pager;
import com.xingfly.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by SuperS on 16/2/29.
 * 管理员 文章编辑页面
 */
@Controller
@RequestMapping("manage/article")
public class ManageArticleController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private WebAppService webAppService;

    //显示创建页面
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String showCreatePage(ModelMap model, HttpSession session) {
        model.addAttribute(AttributeConstant.MAIN_PAGE, "admin/article/editorArticle.vm");
        model.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
        model.addAttribute(AttributeConstant.CATEGORIES, categoryService.getCategories());
        return "admin/index";
    }

    //创建操作
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createAction(Article article, ModelMap model, HttpSession session) {
        String path;
        article.setClicks(0);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        article.setPubDate(formatter.format(new Date()));
        if (StringUtil.isNotEmpty(article.getTitle()) && StringUtil.isNotEmpty(article.getMarkDown()) && StringUtil.isNotEmpty(article.getRemark())) {
            articleService.saveArticle(article);
            path = "redirect:/manage/article";
        } else {
            model.addAttribute(AttributeConstant.ERROR, "有未填选项,请核对后重新发布文章!");
            model.addAttribute(AttributeConstant.MAIN_PAGE, "admin/article/editorArticle.vm");
            model.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
            model.addAttribute(AttributeConstant.CATEGORIES, categoryService.getCategories());
            path = "admin/index";
        }
        return path;
    }

    //显示文章列表
    @RequestMapping(method = RequestMethod.GET)
    public String showListArticle(ModelMap model, @RequestParam(defaultValue = "1") Integer currentPage, HttpSession session) {
        Pager pager = new Pager(currentPage, webAppService.getWebAppDtos().get(0).getAdminPageArticleSize(), articleService.count());
        List<ArticleDto> articles = articleService.getPageArticles(pager);
        model.addAttribute(AttributeConstant.ARTICLES, articles);
        model.addAttribute(AttributeConstant.PAGER, pager);
        model.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
        model.addAttribute(AttributeConstant.MAIN_PAGE, "admin/article/listArticle.vm");
        return "admin/index";
    }

    //通过 ID 显示更新文章页面
    @RequestMapping(value = "update/{articleId:[0-9]+}", method = RequestMethod.GET)
    public String upDateArticle(ModelMap model, HttpSession session, @PathVariable("articleId") Integer articleId) {
        model.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
        ArticleDto articleDto = articleService.getArticle(articleId);
        if (StringUtil.isNotEmpty(articleDto.getTitle())) {
            model.addAttribute(AttributeConstant.ARTICLE, articleDto);
            model.addAttribute(AttributeConstant.MAIN_PAGE, "admin/article/editorArticle.vm");
            model.addAttribute(AttributeConstant.CATEGORIES, categoryService.getCategories());
        } else {
            Pager pager = new Pager(1, 10, articleService.count());
            List<ArticleDto> articles = articleService.getPageArticles(pager);
            model.addAttribute(AttributeConstant.ERROR, "找不到该文章!");
            model.addAttribute(AttributeConstant.MAIN_PAGE, "admin/article/listArticle.vm");
            model.addAttribute(AttributeConstant.PAGER, pager);
            model.addAttribute(AttributeConstant.ARTICLES, articles);
        }
        return "admin/index";
    }

    //通过ID更新文章 操作
    @RequestMapping(value = "update/{articleId:[0-9]+}", method = RequestMethod.POST)
    public String upDateArticleAction(Article article, @PathVariable("articleId") Integer articleId, ModelMap model, HttpSession session) {
        String path;
        if (StringUtil.isNotEmpty(article.getTitle()) && StringUtil.isNotEmpty(article.getMarkDown()) && StringUtil.isNotEmpty(article.getRemark())) {
            article.setClicks(articleService.getArticle(articleId).getClicks());
            article.setPubDate(articleService.getArticle(articleId).getPubDate());
            articleService.updateArticle(article);
            path = "redirect:/manage/article";
        } else {
            model.addAttribute(AttributeConstant.ERROR, "有未填选项,请核对后重新发布文章!");
            model.addAttribute(AttributeConstant.MAIN_PAGE, "admin/article/editorArticle.vm");
            model.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
            model.addAttribute(AttributeConstant.ARTICLE, articleService.getArticle(articleId));
            model.addAttribute(AttributeConstant.CATEGORIES, categoryService.getCategories());
            path = "admin/index";
        }
        return path;
    }

    //通过ID 删除文章
    @RequestMapping("delete/{articleId:[0-9]+}")
    public String deleteArticle(@PathVariable("articleId") Integer articleId, @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage) {
        String path = "redirect:/manage/article";
        articleService.deleteArticle(articleId);
        if (currentPage != 1) {
            path = "redirect:/manage/article/?currentPage=" + currentPage;
        }
        return path;
    }

    //搜索 文章
    @RequestMapping("search")
    public String search(String content, ModelMap model, HttpSession session) {
        Article article = new Article();
        article.setTitle(content);
        model.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
        List<ArticleDto> articleDtos = articleService.searchArticles(article);
        Pager pager = new Pager(1, 10, articleService.searchArticles(article).size());
        if (articleDtos.size() > 0) {
            model.addAttribute(AttributeConstant.ARTICLES, articleDtos);
        } else {
            model.addAttribute(AttributeConstant.ARTICLES, null);
        }
        model.addAttribute(AttributeConstant.PAGER, pager);

        model.addAttribute(AttributeConstant.MAIN_PAGE, "admin/article/listArticle.vm");
        return "admin/index";
    }
}
