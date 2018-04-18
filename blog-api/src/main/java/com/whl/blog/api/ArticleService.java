package com.whl.blog.api;


import com.whl.blog.api.dto.ArticleDto;
import com.whl.blog.api.dto.ArticleLiteDto;
import com.whl.blog.api.pojo.Article;
import com.whl.blog.web.common.pager.mysql.Pager;

import java.util.List;

/**
 * Created by SuperS on 15/12/9.
 */
public interface ArticleService {

    //按照标题搜索文章
    List<ArticleDto> searchArticles(Article article);

    //文章分页列表
    List<ArticleDto> getPageArticles(Pager pager);

    // 获取文章 title,content,pubdate,category,clicks,content
    ArticleDto getArticle(Integer id);

    //获取上一篇文章
    ArticleLiteDto getPreArticle(Integer id);

    //获取下一篇文章
    ArticleLiteDto getNextArticle(Integer id);

    //获取文章列表 article(title,pubdate)
    List<ArticleLiteDto> getArticlesByCategory(int categoryId);

    //归档文章列表 article(title,pubdate)
    List<ArticleLiteDto> getArchive();
    //获取文章简介
    //更新具体文章 about(content)
    //保存或添加具体文章 about(content)

    //更新文章
    void updateArticle(Article article);

    //添加文章
    void saveArticle(Article article);

    //删除文章
    void deleteArticle(Integer id);

    //获取数值
    int count();

    //更新点击数或者浏览量
    void updateClicks(Integer clicks, Integer id);
}
