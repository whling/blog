package com.whl.blog.web.service;

import com.whl.blog.api.ArticleService;
import com.whl.blog.api.dto.ArticleDto;
import com.whl.blog.api.dto.ArticleLiteDto;
import com.whl.blog.api.pojo.Article;
import com.whl.blog.web.common.pager.mysql.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by SuperS on 15/12/13.
 */
@Service("articleServiceImpl")
public class ArticleServiceImpl {

    @Resource
    private ArticleService articleService;

    public List<ArticleDto> searchArticles(Article article) {
        return articleService.searchArticles(article);
    }

    public List<ArticleDto> getPageArticles(Pager pager) {
        return articleService.getPageArticles(pager);
    }


    public ArticleDto getArticle(Integer id) {
        return articleService.getArticle(id);
    }

    public ArticleLiteDto getPreArticle(Integer id) {
        return articleService.getPreArticle(id);
    }

    public ArticleLiteDto getNextArticle(Integer id) {
        return articleService.getNextArticle(id);
    }

    public List<ArticleLiteDto> getArticlesByCategory(int categoryId) {
        return articleService.getArticlesByCategory(categoryId);
    }

    public List<ArticleLiteDto> getArchive() {
        return articleService.getArchive();
    }


    public void updateArticle(Article article) {
        articleService.updateArticle(article);
    }

    public void saveArticle(Article article) {
        articleService.saveArticle(article);
    }

    public void deleteArticle(Integer id) {
        articleService.deleteArticle(id);
    }

    public int count() {
        return articleService.count();
    }

    public void updateClicks(Integer clicks, Integer id) {
        articleService.updateClicks(clicks, id);
    }
}
