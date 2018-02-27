package com.oocl.manre.fragmentstudy.entity;

import java.util.List;

/**
 * Created by manre on 21/02/2018.
 */

public class Page<T> {

    private int currentPage;
    private int pageSize;
    private int totalRecord;
    private List<T> list;

    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getTotalRecord() {
        return totalRecord;
    }
    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
    public List<T> getList() {
        return list;
    }
    public void setList(List<T> list) {
        this.list = list;
    }

}