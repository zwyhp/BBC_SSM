package com.ssm.bbc.messcategory.domain;

public class TmessCategory {
    private int categoryId;
    private String category;
    private String categoryOwner;

    public TmessCategory() {

    }

    public TmessCategory(String category, String categoryOwner) {
        this.category = category;
        this.categoryOwner = categoryOwner;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryOwner() {
        return categoryOwner;
    }

    public void setCategoryOwner(String categoryOwner) {
        this.categoryOwner = categoryOwner;
    }
}
