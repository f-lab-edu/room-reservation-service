package com.ryan.roomreservationservice.domain;

import lombok.Getter;

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
        this.name = name;
        this.description = description;
        this.sortOrder = sortOrder;
        this.depth = depth;
    }
}
