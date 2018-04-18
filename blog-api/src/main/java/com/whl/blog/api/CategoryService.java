package com.whl.blog.api;


import com.whl.blog.api.dto.CategoryDto;
import com.whl.blog.api.pojo.Category;
import com.whl.blog.web.common.pager.mysql.Pager;

import java.util.List;

/**
 * Created by SuperS on 15/12/9.
 */
public interface CategoryService {

    //获取所有分类
     List<CategoryDto> getCategories();

    //更新分类
     void updateCategory(Category category);

    //保存或添加分类
     void saveCategory(Category category);

    // 删除分类
     void deleteCategory(Integer categoryId);

    //获取分类
     Category getCategory(int categoryId);

    //是否存在该categoryId
     boolean exist(int categoryId);

    //分页查找
     List<CategoryDto> getPageCategories(Pager pager);

    //获取总数
     int getCount();
}
