package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.utils.exception.ErrorMessage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SubCategoryTest {
    @Test
    public void 이름값이_null일때_추가하기_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String subCategoryName = null;
        String subCategoryDescription = "글램핑 하위 카테고리";
        int subCategorySort = 1;
        int depth = 1;

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new SubCategory(subCategoryName, subCategoryDescription, subCategorySort, depth));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(illegalArgumentException.getMessage()).isEqualTo(ErrorMessage.PLEASE_END_CORRECT_SUBCATEGORY);
    }

    @Test
    public void 상세정보값이_null일때_추가하기_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String subCategoryName = "글램핑";
        String subCategoryDescription = null;
        int subCategorySort = 1;
        int depth = 1;

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new SubCategory(subCategoryName, subCategoryDescription, subCategorySort, depth));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(illegalArgumentException.getMessage()).isEqualTo(ErrorMessage.PLEASE_END_CORRECT_SUBCATEGORY);
    }

    @Test
    public void 정렬순서값이_0또는_작을때_추가하기_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String subCategoryName = "글램핑";
        String subCategoryDescription = "글램핑 하위 카테고리";
        int subCategorySort = 0;
        int depth = 1;

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new SubCategory(subCategoryName, subCategoryDescription, subCategorySort, depth));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(illegalArgumentException.getMessage()).isEqualTo(ErrorMessage.PLEASE_END_CORRECT_SUBCATEGORY);
    }

    @Test
    public void 깊이값이_0또는_작을때_추가하기_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String subCategoryName = "글램핑";
        String subCategoryDescription = "글램핑 하위 카테고리";
        int subCategorySort = 1;
        int depth = -1;

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new SubCategory(subCategoryName, subCategoryDescription, subCategorySort, depth));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(illegalArgumentException.getMessage()).isEqualTo(ErrorMessage.PLEASE_END_CORRECT_SUBCATEGORY);
    }
}