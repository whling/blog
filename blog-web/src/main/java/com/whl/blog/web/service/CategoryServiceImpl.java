package com.whl.blog.web.service;

import com.whl.blog.api.CategoryService;
import com.whl.blog.api.dto.CategoryDto;
import com.whl.blog.api.pojo.Category;
import com.whl.blog.web.common.pager.mysql.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by SuperS on 15/12/13.
 */
@Service("categoryServiceImpl")
public class CategoryServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Resource
    private CategoryService categoryService;

    public List<CategoryDto> getCategories() {
        return categoryService.getCategories();
    }

    public void updateCategory(Category category) {
        categoryService.updateCategory(category);
    }

    public void saveCategory(Category category) {
        categoryService.saveCategory(category);
    }

    public void deleteCategory(Integer categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    public List<CategoryDto> getPageCategories(Pager pager) {
        return categoryService.getPageCategories(pager);
    }

    public int getCount() {
        return categoryService.getCount();
    }

    public Category getCategory(int categoryId) {
        return categoryService.getCategory(categoryId);
    }


    public boolean exist(int categoryId) {
        return categoryService.exist(categoryId);
    }
}
