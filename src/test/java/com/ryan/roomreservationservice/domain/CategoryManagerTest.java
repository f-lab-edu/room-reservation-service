package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.utils.exception.ErrorMessage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoryManagerTest {

    @Test
    public void 카테고리_추가하기_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String categoryName = "팬션/풀빌라";
        String categoryDescription = "팬션/풀빌라 상위 카테고리";
        int sortOrder  =1;
        List<SubCategory> subCategories = new ArrayList<>();
        Category category = new Category(categoryName, categoryDescription, sortOrder, subCategories);

        List<Category> categories = new ArrayList<>();
        CategoryManager categoryManager = new CategoryManager(categories);

        // when(실행): 어떠한 함수를 실행하면
        List<Category> addCategory = categoryManager.addCategory(category);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(addCategory.size()).isEqualTo(1);
        assertThat(addCategory).contains(category);
    }

    @Test
    public void 카테고리_값이_null일때_추가하기_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        List<Category> categories = new ArrayList<>();
        CategoryManager categoryManager = new CategoryManager(categories);

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> categoryManager.addCategory(null));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(illegalArgumentException.getMessage()).isEqualTo(ErrorMessage.PLEASE_END_CORRECT_CATEGORY);
    }

    @Test
    public void 카테고리_이름값이_null일때_추가하기_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String categoryName = null;
        String categoryDescription = "팬션/풀빌라 상위 카테고리";
        int sortOrder  =1;
        List<SubCategory> subCategories = new ArrayList<>();
        Category category = new Category(categoryName, categoryDescription, sortOrder, subCategories);

        List<Category> categories = new ArrayList<>();
        CategoryManager categoryManager = new CategoryManager(categories);

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> categoryManager.addCategory(category));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(illegalArgumentException.getMessage()).isEqualTo(ErrorMessage.PLEASE_END_CORRECT_CATEGORY);
    }

    @Test
    public void 서브카테고리_추가하기_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String categoryName = "팬션/풀빌라";
        String categoryDescription = "팬션/풀빌라 상위 카테고리";
        int sortOrder  =1;
        List<SubCategory> subCategories = new ArrayList<>();
        Category category = new Category(categoryName, categoryDescription, sortOrder, subCategories);

        String subCategoryName = "글램핑";
        String subCategoryDescription = "글램핑 하위 카테고리";
        int subCategorySort = 1;
        int depth = 1;
        SubCategory subCategory = new SubCategory(subCategoryName, subCategoryDescription, subCategorySort, depth);

        List<Category> categories = new ArrayList<>();
        categories.add(category);

        CategoryManager categoryManager = new CategoryManager(categories);

        // when(실행): 어떠한 함수를 실행하면
        List<Category> addSubCategory = categoryManager.addSubCategory(category, subCategory);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(addSubCategory.size()).isEqualTo(1);
        assertThat(addSubCategory.get(0).getSubCategories().size()).isEqualTo(1);
        assertThat(addSubCategory.get(0).getSubCategories()).contains(subCategory);
    }

    @Test
    public void 서브카테고리_추가시_상위카테고리_값이_null일때_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String categoryName = "팬션/풀빌라";
        String categoryDescription = "팬션/풀빌라 상위 카테고리";
        int sortOrder  =1;
        List<SubCategory> subCategories = new ArrayList<>();
        Category category = new Category(categoryName, categoryDescription, sortOrder, subCategories);

        String categoryName2 = "찾을 수 없는 카테고리 이픔";
        Category category2 = new Category(categoryName2, categoryDescription, sortOrder, subCategories);

        String subCategoryName = "글램핑";
        String subCategoryDescription = "글램핑 하위 카테고리";
        int subCategorySort = 1;
        int depth = 1;
        SubCategory subCategory = new SubCategory(subCategoryName, subCategoryDescription, subCategorySort, depth);

        List<Category> categories = new ArrayList<>();
        categories.add(category);

        CategoryManager categoryManager = new CategoryManager(categories);

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> categoryManager.addSubCategory(category2, subCategory));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(illegalArgumentException.getMessage()).isEqualTo(ErrorMessage.NOT_FOUND_CATEGORY);
    }

    @Test
    public void 특정_카테고리의_하위카테고리_조회하기_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String categoryName = "팬션/풀빌라";
        String categoryDescription = "팬션/풀빌라 상위 카테고리";
        int sortOrder  =1;
        List<SubCategory> subCategories = new ArrayList<>();
        Category category = new Category(categoryName, categoryDescription, sortOrder, subCategories);

        String subCategoryName = "글램핑";
        String subCategoryDescription = "글램핑 하위 카테고리";
        int subCategorySort = 1;
        int depth = 1;
        SubCategory subCategory = new SubCategory(subCategoryName, subCategoryDescription, subCategorySort, depth);

        List<Category> categories = new ArrayList<>();
        categories.add(category);
        subCategories.add(subCategory);

        CategoryManager categoryManager = new CategoryManager(categories);

        // when(실행): 어떠한 함수를 실행하면
        Category getSubCategories = categoryManager.getSubCategories(categoryName);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(getSubCategories).isNotNull();
        assertThat(getSubCategories.getSubCategories().size()).isEqualTo(1);
        assertThat(getSubCategories.getSubCategories().get(0)).isEqualTo(subCategory);
    }
    
    @Test
    public void 특정_카테고리_하위카테고리_null값일때_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String categoryName = "팬션/풀빌라";
        String categoryDescription = "팬션/풀빌라 상위 카테고리";
        int sortOrder  =1;
        List<SubCategory> subCategories = new ArrayList<>();
        Category category = new Category(categoryName, categoryDescription, sortOrder, subCategories);

        List<Category> categories = new ArrayList<>();
        categories.add(category);

        CategoryManager categoryManager = new CategoryManager(categories);

        String searchCategoryName = "값을 찾아도 없다...";

        // when(실행): 어떠한 함수를 실행하면
        Category getSubCategories = categoryManager.getSubCategories(searchCategoryName);
        
        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(getSubCategories).isNull();;
    }

}