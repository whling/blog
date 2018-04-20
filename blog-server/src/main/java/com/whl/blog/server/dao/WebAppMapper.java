package com.whl.blog.server.dao;

import com.whl.blog.api.dto.WebAppDto;
import com.whl.blog.api.pojo.WebApp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SuperS on 16/3/16.
 */
@Repository
public interface WebAppMapper {

    void save(WebApp webApp);

    void update(WebApp webApp);

    WebAppDto getWebDto(Integer id);

    Integer getArticlesView();

    Integer count();

    List<WebApp> getWebDtos();
}
