package com.whl.blog.server.dao;

import com.whl.blog.api.dto.ArticleDto;
import com.whl.blog.api.dto.ArticleLiteDto;
import com.whl.blog.api.pojo.Article;
import com.whl.blog.web.common.pager.mysql.Pager;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SuperS on 16/3/11.
 */
@Repository
public interface ArticleMapper {
    //搜索文章根据文章标题
    List<ArticleDto> search(Article a_title);

    //分页
    List<ArticleDto> pagerAction(Pager pager);

    //获取文章Dto
    ArticleDto getArticleDto(Integer id);

    //获取上一篇文章
    ArticleLiteDto getPreArticleDto(Integer id);

    //获取下一篇文章
    ArticleLiteDto getNextArticleDto(Integer id);

    //获取某分类下文章
    List<ArticleLiteDto> getByCategory(int categoryId);

    //归档
    List<ArticleLiteDto> archive();

    //更新点击
    void updateArticleClicks(Integer clicks, Integer id);

    //更新文章
    void update(Article article);

    //保存文章
    void save(Article article);

    //删除文章
    void delete(Integer id);

    //数量统计
    int count();
}
