package com.whl.blog.server.dao;

import com.whl.blog.api.dto.CategoryDto;
import com.whl.blog.api.pojo.Category;
import com.whl.blog.web.common.pager.mysql.Pager;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SuperS on 16/3/8.
 */
@Repository
public interface CategoryMapper {

    //获取分类列表
    List<CategoryDto> all();

    //更新分类
    void update(Category category);

    //保存分类
    void save(Category category);

    //删除分类
    void delete(Integer id);

    //获取分类
    Category get(Integer id);

    //是否存在该categoryId >0存在
    int exist(int categoryId);

    //分页查询
    List<CategoryDto> pagenation(Pager pager);

    //总数
    int count();
}
