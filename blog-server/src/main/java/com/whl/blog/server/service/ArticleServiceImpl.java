package com.whl.blog.server.service;

import com.whl.blog.api.ArticleService;
import com.whl.blog.api.dto.ArticleDto;
import com.whl.blog.api.dto.ArticleLiteDto;
import com.whl.blog.api.pojo.Article;
import com.whl.blog.server.dao.ArticleMapper;
import com.whl.blog.web.common.pager.mysql.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by SuperS on 15/12/13.
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public List<ArticleDto> searchArticles(Article article) {
        List<ArticleDto> articleDtoList = null;
        try {
            articleDtoList = articleMapper.search(article);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleDtoList;
    }

    @Override
    public List<ArticleDto> getPageArticles(Pager pager) {
        List<ArticleDto> articleDtoList = null;
        try {
            articleDtoList = articleMapper.pagerAction(pager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleDtoList;
    }


    @Override
    public ArticleDto getArticle(Integer id) {
        ArticleDto articleDto = null;
        try {
            articleDto = articleMapper.getArticleDto(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleDto;
    }

    @Override
    public ArticleLiteDto getPreArticle(Integer id) {
        ArticleLiteDto articleLiteDto = null;
        try {
            articleLiteDto = articleMapper.getPreArticleDto(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleLiteDto;
    }

    @Override
    public ArticleLiteDto getNextArticle(Integer id) {
        ArticleLiteDto articleLiteDto = null;
        try {
            articleLiteDto = articleMapper.getNextArticleDto(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return articleLiteDto;
    }

    @Override
    public List<ArticleLiteDto> getArticlesByCategory(int categoryId) {
        List<ArticleLiteDto> articleLiteDtos = null;
        try {
            articleLiteDtos = articleMapper.getByCategory(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleLiteDtos;
    }

    @Override
    public List<ArticleLiteDto> getArchive() {
        List<ArticleLiteDto> articleLiteDtos = null;
        try {
            articleLiteDtos = articleMapper.archive();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleLiteDtos;
    }


    @Override
    public void updateArticle(Article article) {
        try {
            articleMapper.update(article);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveArticle(Article article) {
        try {
            articleMapper.save(article);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteArticle(Integer id) {
        try {
            articleMapper.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int count() {
        int count = 0;
        try {
            count = articleMapper.count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void updateClicks(Integer clicks, Integer id) {
        try {
            articleMapper.updateArticleClicks(clicks, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
