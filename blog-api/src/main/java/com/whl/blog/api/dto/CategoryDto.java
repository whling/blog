package com.whl.blog.api.dto;

import java.io.Serializable;

/**
 * Created by SuperS on 16/2/23.
 * id    分类id
 * name  分类名称
 * count 分类中的文章数量
 */
public class CategoryDto implements Serializable {
    private Integer id;
    private String name;
    private Integer count;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
