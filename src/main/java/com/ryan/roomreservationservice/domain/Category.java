package com.ryan.roomreservationservice.domain;

import lombok.Getter;

import java.util.List;

public class Category {
    private Long categoryId;
    @Getter
    private String name;
    @Getter
    private String description;
    @Getter
    private int sortOrder;
    @Getter
    private List<SubCategory> subCategories;

    public Category(String name, String description, int sortOrder, List<SubCategory> subCategories) {
        this.name = name;
        this.description = description;
        this.sortOrder = sortOrder;
        this.subCategories = subCategories;
    }

    public void createSubCategory(SubCategory subCategory) {
        this.subCategories.add(subCategory);
    }
}
