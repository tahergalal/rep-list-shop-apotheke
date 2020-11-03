package com.shopapotheke.githubrepositorylist.models;

public enum PageSize {
    TEN(10),
    FIFTY(50),
    ONE_HUNDRED(100);

    PageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    int pageSize;

    public int getPageSize() {
        return pageSize;
    }
}
