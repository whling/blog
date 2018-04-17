package com.whl.blog.web.common.pager.oracle;

import java.io.Serializable;

/**
 * Created by whling on 2017/6/14.
 *
 */
public class Pager implements Serializable {

    private Integer currentPage;
    private Integer pageSize;

    private Integer originalCurrnetPage;
    private Integer originalPageSize;

    public Pager() {
    }

    public Pager(Integer currentPage, Integer pageSize) {
        this.setCurrentPage(currentPage);
        this.setPageSize(pageSize);
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize < 1) {
            pageSize = 10;
        }
        this.pageSize = currentPage + pageSize -1;
        this.originalPageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        if (currentPage < 0) {
            currentPage = 0;
        }
        this.currentPage = currentPage + 1;
        this.originalCurrnetPage = currentPage;
    }

    public Integer getOriginalCurrnetPage() {
        return originalCurrnetPage;
    }

    public Integer getOriginalPageSize() {
        return originalPageSize;
    }

}
