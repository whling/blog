package com.whl.blog.web.common.pager.mysql;

import java.io.Serializable;

/**
 * Created by whling on 2017/6/14.
 *
 */
public class Pager implements Serializable {

    private Integer start;
    private Integer size;

    public Pager() {
    }

    public Pager(Integer currentPage, Integer pageSize) {
        pageSize = pageSize == null || pageSize < 10? 0: pageSize;
        currentPage = currentPage==null || currentPage < 0? 0: (currentPage/pageSize);

        this.setStart(currentPage, pageSize);
        this.setSize(pageSize);
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer currentPage, Integer pageSize) {
        this.start = currentPage * pageSize;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
