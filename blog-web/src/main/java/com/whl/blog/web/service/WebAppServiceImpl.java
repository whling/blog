package com.whl.blog.web.service;

import com.whl.blog.api.WebAppService;
import com.whl.blog.api.dto.WebAppDto;
import com.whl.blog.api.pojo.WebApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by SuperS on 16/3/16.
 */
@Service("webAppServiceImpl")
public class WebAppServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(WebAppServiceImpl.class);

    @Resource
    private WebAppService webAppService;

    public void saveWebApp(WebApp webApp) {
        webAppService.saveWebApp(webApp);
    }

    public void updateWebApp(WebApp webApp) {
        webAppService.updateWebApp(webApp);
    }

    public WebAppDto getWebDtoWebApp(Integer id) {
        return webAppService.getWebDtoWebApp(id);
    }

    public Integer getArticlesView() {
        return webAppService.getArticlesView();
    }

    public List<WebApp> getWebAppDtos() {
        return webAppService.getWebAppDtos();
    }

    public boolean webAppNotNull() {
        return webAppService.webAppNotNull();
    }
}
