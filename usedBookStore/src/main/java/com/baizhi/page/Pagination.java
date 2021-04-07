package com.baizhi.page;

import java.util.ArrayList;
import java.util.List;

public class Pagination {

    private int pageNum;
    private int pageSize;
    private int totalCount;
    private List<Integer> pages = new ArrayList<>();

    public Pagination(Integer pageSize, Integer pageNum, int totalCount){
        this.totalCount = totalCount;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        for (int i = 1; i <=pageNum; i++) {
            pages.add(i);
        }
        int pageCount = totalCount/pageSize + ((totalCount % pageSize ==0)?0:1);
        if (pageCount > pageNum){
            for (int i = pageNum+1; i <= pageCount; i++) {
                pages.add(i);
            }
        }
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

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }


}
