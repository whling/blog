package com.whl.blog.server.service;

import com.whl.blog.api.WebAppService;
import com.whl.blog.api.dto.WebAppDto;
import com.whl.blog.api.pojo.WebApp;
import com.whl.blog.server.dao.WebAppMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by SuperS on 16/3/16.
 */
@Service("webAppService")
public class WebAppServiceImpl implements WebAppService {
    @Resource
    private WebAppMapper webAppMapper;

    @Override
    public void saveWebApp(WebApp webApp) {
        try {
            webAppMapper.save(webApp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateWebApp(WebApp webApp) {
        try {
            webAppMapper.update(webApp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public WebAppDto getWebDtoWebApp(Integer id) {
        WebAppDto webAppDto = null;
        try {
            webAppDto = webAppMapper.getWebDto(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return webAppDto;
    }

    @Override
    public Integer getArticlesView() {
        Integer totalViews = 0;
        try {
            totalViews = webAppMapper.getArticlesView();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalViews;
    }

    @Override
    public List<WebApp> getWebAppDtos() {
        List<WebApp> webApps = null;
        try {
            webApps = webAppMapper.getWebDtos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return webApps;
    }

    @Override
    public boolean webAppNotNull() {
        Integer total = 0;
        try {
            total = webAppMapper.count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total > 0 ? true : false;
    }
}
