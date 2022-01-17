package com.chan.chang.common.entity;

import java.util.List;

/**
 * 分页结果封装类
 * @param <T>
 */
public class MyPage<T> {
    //当前页
    private int pageNum;
    //每页多少条
    private int pageSize;
    //当前页实际多少条
    private int size;
    //当前页开始行
    private int startRow;
    //当前页结束行
    private int endRow;
    //一共多少条
    private int total;
    //一共多少页
    private int pages;
    //当前页数据列表
    private List<T> list;

    public MyPage(int pageNum, int pageSize, int total, int pages, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.pages = pages;
        this.list = list;
    }

    public MyPage(int pageNum, int pageSize, int size, int startRow, int endRow, int total, int pages, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.size = size;
        this.startRow = startRow;
        this.endRow = endRow;
        this.total = total;
        this.pages = pages;
        this.list = list;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
