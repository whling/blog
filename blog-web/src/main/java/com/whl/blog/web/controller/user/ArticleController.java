package com.whl.blog.web.controller.user;

import com.xingfly.constant.AttributeConstant;
import com.xingfly.model.Article;
import com.xingfly.model.dto.ArticleDto;
import com.xingfly.model.dto.ArticleLiteDto;
import com.xingfly.service.ArticleService;
import com.xingfly.service.WebAppService;
import com.xingfly.util.Pager;
import com.xingfly.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by SuperS on 16/2/26.
 * 访客 文章页面
 */
@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private WebAppService webAppService;
    //显示 详细文章
    @RequestMapping("article/{articleId:[0-9]+}")
    public String showArticle(@PathVariable("articleId")Integer articleId, ModelMap model){
        ArticleDto articleDto = articleService.getArticle(articleId);
        model.addAttribute(AttributeConstant.MAIN_PAGE, "user/article/detail.vm");
        model.addAttribute(AttributeConstant.WEB_APP_DTO,webAppService.getWebDtoWebApp(webAppService.getWebAppDtos().get(0).getId()));

        if(StringUtil.isNotEmpty(articleDto.getTitle())) {
            //点击量+1
            articleService.updateClicks(articleDto.getClicks() + 1, articleDto.getId());
            //更新一下用于显示
            articleDto.setClicks(articleDto.getClicks() + 1);
            //获取上一篇文章
            ArticleLiteDto preArticle = articleService.getPreArticle(articleDto.getId());
            //获取下一篇文章
            ArticleLiteDto nextArticle = articleService.getNextArticle(articleDto.getId());
            model.addAttribute(AttributeConstant.ARTICLE, articleDto);
            model.addAttribute("preArticle", preArticle);
            model.addAttribute("nextArticle", nextArticle);
        }else{
            model.addAttribute(AttributeConstant.ERROR,"没有此文章");
        }
        return "index";
    }
    //搜索操作
    @RequestMapping(value = "search",method = RequestMethod.POST)
    public String search(String content, ModelMap model) {
        Article article = new Article();
        article.setTitle(content);
        model.addAttribute(AttributeConstant.WEB_APP_DTO,webAppService.getWebDtoWebApp(webAppService.getWebAppDtos().get(0).getId()));
        List<ArticleDto> articleDtos = articleService.searchArticles(article);
        if (articleDtos.size()>0) {
            Pager pager = new Pager(1, articleService.searchArticles(article).size(), articleService.searchArticles(article).size());
            model.addAttribute(AttributeConstant.PAGER, pager);
            model.addAttribute(AttributeConstant.ARTICLES, articleDtos);
            model.addAttribute(AttributeConstant.RETURN_INFO,"搜索内容已经全部显示");
        }else{
            model.addAttribute(AttributeConstant.RETURN_INFO,"没有找到该内容");
        }
        //搜索不分页
        model.addAttribute("search","search");

        model.addAttribute(AttributeConstant.MAIN_PAGE, "user/article/articleList.vm");
        return "index";
    }
    //直接访问搜索 跳转到 搜夜
    @RequestMapping(value = "search",method = RequestMethod.GET)
    public String showSearch(){
        return "redirect:/";
    }
}
