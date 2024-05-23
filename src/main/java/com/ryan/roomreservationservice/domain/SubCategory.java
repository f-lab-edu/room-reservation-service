package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.utils.exception.ErrorMessage;
import lombok.Getter;

import java.util.Objects;

public class SubCategory {
    private Long subCategoryId;
    @Getter
    private String name;
    @Getter
    private String description;
    @Getter
    private int sortOrder;
    @Getter
    private int depth;

    public SubCategory(String name, String description, int sortOrder, int depth) {
        if (Objects.isNull(name) || Objects.isNull(description) || sortOrder <= 0 || depth <= 0)
            throw new IllegalArgumentException(ErrorMessage.PLEASE_END_CORRECT_SUBCATEGORY);

        this.name = name;
        this.description = description;
        this.sortOrder = sortOrder;
        this.depth = depth;
    }
}
