package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.utils.exception.ErrorMessage;

import java.util.List;
import java.util.Objects;

public class CategoryManager {
    private List<Category> categories;

    public CategoryManager(List<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        if(Objects.isNull(category))
            throw new IllegalArgumentException(ErrorMessage.PLEASE_END_CORRECT_CATEGORY);

        this.categories.add(category);
    }

    public void addSubCategory(String categoryName, SubCategory subCategory) {
        if(Objects.isNull(subCategory))
            throw new IllegalArgumentException(ErrorMessage.PLEASE_END_CORRECT_SUBCATEGORY);

        Category foundCategory = this.categories.stream()
                .filter((categoryInfo) -> categoryInfo.getName().equals(categoryName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.NOT_FOUND_CATEGORY));

        foundCategory.createSubCategory(subCategory);
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
