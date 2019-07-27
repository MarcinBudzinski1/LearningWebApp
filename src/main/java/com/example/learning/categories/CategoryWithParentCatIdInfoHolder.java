package com.example.learning.categories;

public interface CategoryWithParentCatIdInfoHolder extends CategoryInfoHolder {
    void setParentCatId(String id);
    String getParentCatId();
}
