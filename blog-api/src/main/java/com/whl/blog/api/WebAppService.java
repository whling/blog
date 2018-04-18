package com.whl.blog.api;


import com.whl.blog.api.dto.WebAppDto;
import com.whl.blog.api.pojo.WebApp;

import java.util.List;

/**
 * Created by SuperS on 16/3/16.
 */
public interface WebAppService {

    void saveWebApp(WebApp webApp);

    void updateWebApp(WebApp webApp);

    WebAppDto getWebDtoWebApp(Integer id);

    Integer getArticlesView();

    boolean webAppNotNull();

    List<WebApp> getWebAppDtos();
}
