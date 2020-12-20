package com.creative.task.domain.dto;

public class Result<T> {

    private Integer currentPage;
    private Integer totalPage;
    private Iterable<T> resultCurrentPage;

    public Result() { }
    public Result(Integer currentPage, Integer totalPage, Iterable<T> resultCurrentPage) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.resultCurrentPage = resultCurrentPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Iterable<T> getResultCurrentPage() {
        return resultCurrentPage;
    }
    public void setResultCurrentPage(Iterable<T> resultCurrentPage) {
        this.resultCurrentPage = resultCurrentPage;
    }
}
