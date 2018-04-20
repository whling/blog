package com.whl.blog.server.dao;

import com.whl.blog.api.pojo.About;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SuperS on 16/1/7.
 */
@Repository
public interface AboutMapper {
    //获取About
    About getAbout(Integer id);

    //更新About
    void upDate(About about);

    //存储About
    void save(About about);

    //删除About
    void delete(Integer id);

    //计数About
    int count();

    //About列表
    List<About> list();
}
