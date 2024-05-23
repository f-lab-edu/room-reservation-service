package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.utils.exception.ErrorMessage;

import java.util.List;
import java.util.Objects;

public class CategoryManager {
    private List<Category> categories;

    public CategoryManager(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> addCategory(Category category) {
        if(Objects.isNull(category) || Objects.isNull(category.getName()) || Objects.isNull(category.getDescription()) || category.getSortOrder() <= 0)
            throw new IllegalArgumentException(ErrorMessage.PLEASE_END_CORRECT_CATEGORY);

        this.categories.add(category);
        return this.categories;
    }

    public List<Category> addSubCategory(Category category, SubCategory subCategory) {
        if(Objects.isNull(category) || Objects.isNull(category.getName()) || Objects.isNull(category.getDescription()) || category.getSortOrder() <= 0)
            throw new IllegalArgumentException(ErrorMessage.PLEASE_END_CORRECT_CATEGORY);

        if(Objects.isNull(subCategory) || Objects.isNull(subCategory.getName()) || Objects.isNull(subCategory.getDescription()) || subCategory.getSortOrder() <= 0 || subCategory.getDepth() <= 0)
            throw new IllegalArgumentException(ErrorMessage.PLEASE_END_CORRECT_SUBCATEGORY);

        Category findCategory = this.categories.stream()
                .filter((categoryInfo) -> categoryInfo.getName().equals(category.getName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.NOT_FOUND_CATEGORY));

        findCategory.createSubCategory(subCategory);

        return this.categories;
    }

    public List<Category> getCategories() {
        return this.categories;
    }

    public Category getSubCategories(String name) {
        return this.categories.stream()
                .filter((categoryInfo) -> categoryInfo.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
