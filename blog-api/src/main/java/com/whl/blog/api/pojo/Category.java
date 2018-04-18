package com.whl.blog.api.pojo;

import java.io.Serializable;

/**
 * Created by SuperS on 16/2/23.
 * 对应   t_category表
 * 分类
 * id   分类id
 * name 分类名称
 */
public class Category implements Serializable {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}