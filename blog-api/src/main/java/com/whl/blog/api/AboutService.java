package com.whl.blog.api;


import com.whl.blog.api.pojo.About;

import java.util.List;

/**
 * Created by SuperS on 16/1/7.
 */
public interface AboutService {

    About getAbout(Integer id);

    void upDate(About about);

    void save(About about);

    void delete(Integer id);

    int count();

    List<About> list();
}
