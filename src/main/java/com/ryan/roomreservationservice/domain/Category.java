package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.utils.exception.ErrorMessage;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

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
        if(Objects.isNull(name) || Objects.isNull(description) || sortOrder <= 0)
            throw new IllegalArgumentException(ErrorMessage.PLEASE_END_CORRECT_CATEGORY);

        this.name = name;
        this.description = description;
        this.sortOrder = sortOrder;
        this.subCategories = subCategories;
    }

    public void createSubCategory(SubCategory subCategory) {
        this.subCategories.add(subCategory);
    }
}
