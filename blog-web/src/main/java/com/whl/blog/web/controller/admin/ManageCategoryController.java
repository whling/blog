package com.whl.blog.web.controller.admin;

import com.xingfly.constant.AttributeConstant;
import com.xingfly.model.Category;
import com.xingfly.model.dto.UserDto;
import com.xingfly.service.CategoryService;
import com.xingfly.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by SuperS on 16/3/1.
 *
 * 管理员 分类编辑页面
 */
@Controller
@RequestMapping("/manage/category")
public class ManageCategoryController {
    @Autowired
    private CategoryService categoryService;
    //显示分类编辑页面
    @RequestMapping(method = RequestMethod.GET)
    public String showCategoriesPage(ModelMap model, HttpSession session) {

        model.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
        model.addAttribute(AttributeConstant.CATEGORIES, categoryService.getCategories());
        model.addAttribute(AttributeConstant.MAIN_PAGE, "admin/category/editor.vm");
        return "admin/index";
    }
    //创建分类
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createAction(String categoryName, ModelMap model, HttpSession session) {
        model.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
        model.addAttribute(AttributeConstant.MAIN_PAGE, "admin/category/editor.vm");
        if (StringUtil.isNotEmpty(categoryName)) {
            Category category = new Category();
            category.setName(categoryName);
            categoryService.saveCategory(category);
        } else {
            model.addAttribute(AttributeConstant.ERROR, "分类名称未填写!");
        }
        model.addAttribute(AttributeConstant.CATEGORIES, categoryService.getCategories());
        return "admin/index";
    }
    //显示 更新分类页面
    @RequestMapping(value = "/update/{categoryId:[0-9]+}", method = RequestMethod.GET)
    public String upDatePage(@PathVariable("categoryId") Integer categoryId, ModelMap model, HttpSession session) {
        model.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
        model.addAttribute(AttributeConstant.MAIN_PAGE, "admin/category/editor.vm");
        Category category = categoryService.getCategory(categoryId);
        if (StringUtil.isNotEmpty(category.getName())) {
            model.addAttribute(AttributeConstant.CATEGORY, categoryService.getCategory(categoryId));
        }else{
            model.addAttribute(AttributeConstant.ERROR,"找不到该分类!");
        }
        model.addAttribute(AttributeConstant.CATEGORIES, categoryService.getCategories());
        return "admin/index";
    }
    //更新分类
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String upDateAction(String categoryName, Integer categoryId, ModelMap model, HttpSession session){
        model.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
        model.addAttribute(AttributeConstant.MAIN_PAGE, "admin/category/editor.vm");
        Category category = new Category();
        category.setName(categoryName);
        category.setId(categoryId);
        categoryService.updateCategory(category);
        model.addAttribute(AttributeConstant.CATEGORIES, categoryService.getCategories());
        model.addAttribute(AttributeConstant.RETURN_INFO,"修改成功!");
        model.addAttribute(AttributeConstant.CATEGORY,category);
        return "admin/index";
    }

    //删除分类
    @RequestMapping(value = "/delete/{categoryId:[0-9]+}")
    public String deleteAction(@PathVariable("categoryId")Integer categoryId, ModelMap model, HttpSession session){
        model.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
        model.addAttribute(AttributeConstant.MAIN_PAGE, "admin/category/editor.vm");
        Category category = categoryService.getCategory(categoryId);
        if(StringUtil.isNotEmpty(category.getName())){
            categoryService.deleteCategory(categoryId);
            model.addAttribute(AttributeConstant.RETURN_INFO,"删除成功!");
        }else{
            model.addAttribute(AttributeConstant.ERROR,"找不到该分类");
        }
        model.addAttribute(AttributeConstant.CATEGORIES,categoryService.getCategories());
        return "admin/index";
    }

}
