package com.example.financialmanagement.model;

import java.util.List;

public class PageList {
    private Integer currentPage;//当前页
    private int pageSize;//每页显示记录条数
    private int totalPage;//总页数
    private List<BasicRecord> dataList;//每页显示的数据
    private int start;//开始数据

    public Integer getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public List<BasicRecord> getDataList() {
        return dataList;
    }
    public void setDataList(List<BasicRecord> dataList) {
        this.dataList = dataList;
    }
    public int getstart() {
        return start;
    }
    public void setstart(int start) {
        this.start = start;
    }
}
