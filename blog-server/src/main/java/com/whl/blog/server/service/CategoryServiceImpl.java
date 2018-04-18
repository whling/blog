package com.whl.blog.server.service;

import com.whl.blog.api.CategoryService;
import com.whl.blog.api.dto.CategoryDto;
import com.whl.blog.api.pojo.Category;
import com.whl.blog.server.dao.CategoryMapper;
import com.whl.blog.web.common.pager.mysql.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by SuperS on 15/12/13.
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getCategories() {
        List<CategoryDto> categories = null;
        try {
            categories = categoryMapper.all();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public void updateCategory(Category category) {
        try {
            categoryMapper.update(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveCategory(Category category) {
        try {
            categoryMapper.save(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        try {
            categoryMapper.delete(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CategoryDto> getPageCategories(Pager pager) {
        List<CategoryDto> categoryDtos = null;
        try {
            categoryDtos = categoryMapper.pagenation(pager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryDtos;
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = categoryMapper.count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Category getCategory(int categoryId) {
        Category category = null;
        try {
            category = categoryMapper.get(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public boolean exist(int categoryId) {
        int state = 0;
        try {
            state = categoryMapper.exist(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return state > 0 ? true : false;
    }
}
