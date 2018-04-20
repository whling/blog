package com.whl.blog.web.service;

import com.whl.blog.api.AboutService;
import com.whl.blog.api.pojo.About;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by SuperS on 16/1/7.
 */
@Service("aboutServiceImpl")
public class AboutServiceImpl {

    @Resource
    private AboutService aboutService;

    public int count() {
        return aboutService.count();
    }

    public About getAbout(Integer id) {
        return aboutService.getAbout(id);
    }

    public void upDate(About about) {
        aboutService.upDate(about);
    }

    public List<About> list() {
        return aboutService.list();
    }

    public void save(About about) {
        aboutService.save(about);
    }

    public void delete(Integer id) {
        aboutService.delete(id);
    }
}
