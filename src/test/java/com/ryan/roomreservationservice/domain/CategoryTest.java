package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.utils.exception.ErrorMessage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    public void 이름값이_null일때_추가하기_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String name = null;
        String description = "팬션/풀빌라 상위 카테고리";
        int sortOrder  = 1;
        List<SubCategory> subCategories = new ArrayList<>();

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Category(name, description, sortOrder, subCategories));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(illegalArgumentException.getMessage()).isEqualTo(ErrorMessage.PLEASE_END_CORRECT_CATEGORY);
    }

    @Test
    public void 상세정보값이_null일때_추가하기_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String name = "팬션/풀라";
        String description =  "팬션/풀빌라 상위 카테고리";
        int sortOrder  = 0;
        List<SubCategory> subCategories = new ArrayList<>();

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Category(name, description, sortOrder, subCategories));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(illegalArgumentException.getMessage()).isEqualTo(ErrorMessage.PLEASE_END_CORRECT_CATEGORY);
    }

    @Test
    public void 정렬순서값이_0보다_작을때_추가하기_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String name = "팬션/풀라";
        String description = "팬션/풀빌라 상위 카테고리";
        int sortOrder  = -1;
        List<SubCategory> subCategories = new ArrayList<>();

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Category(name, description, sortOrder, subCategories));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(illegalArgumentException.getMessage()).isEqualTo(ErrorMessage.PLEASE_END_CORRECT_CATEGORY);
    }
}