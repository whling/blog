package com.whl.blog.server.service;

import com.whl.blog.api.AboutService;
import com.whl.blog.api.pojo.About;
import com.whl.blog.server.dao.AboutMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by SuperS on 16/1/7.
 */
@Service("aboutService")
public class AboutServiceImpl implements AboutService {

    @Resource
    private AboutMapper aboutMapper;

    @Override
    public int count() {
        Integer count = 0;
        try {
            count = aboutMapper.count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public About getAbout(Integer id) {
        About about = new About();
        try {
            about = aboutMapper.getAbout(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return about;
    }

    @Override
    public void upDate(About about) {
        try {
            aboutMapper.upDate(about);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<About> list() {
        List<About> abouts = null;
        try {
            abouts = aboutMapper.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return abouts;
    }

    @Override
    public void save(About about) {
        try {
            aboutMapper.save(about);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            aboutMapper.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
