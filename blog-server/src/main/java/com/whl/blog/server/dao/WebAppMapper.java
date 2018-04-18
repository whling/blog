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

    public void save(WebApp webApp) throws Exception;

    public void update(WebApp webApp) throws Exception;

    public WebAppDto getWebDto(Integer id) throws Exception;

    public Integer getArticlesView() throws Exception;

    public Integer count() throws Exception;

    public List<WebApp> getWebDtos() throws Exception;
}
