package com.ryan.roomreservationservice.domain;

import lombok.Getter;

public class SubCategory {
    private Long subCategoryId;
    @Getter
    private String name;
    @Getter
    private String description;
    @Getter
    private int sort;
    @Getter
    private int depth;

    public SubCategory(String name, String description, int sort, int depth) {
        this.name = name;
        this.description = description;
        this.sort = sort;
        this.depth = depth;
    }
}
