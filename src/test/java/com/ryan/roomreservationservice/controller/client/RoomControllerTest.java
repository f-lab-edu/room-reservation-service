package com.ryan.roomreservationservice.controller.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoomClientController.class)
@DisplayName("객실 API")
class RoomControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void 객실조회_시작일_요청포맷이_맞지을때() throws Exception {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String start = "2024-01-01T00:00:00";
        String end = "2024-01-31T23:59:59.000Z";

        // when(실행): 어떠한 함수를 실행하면
        ResultActions resultActions = mvc.perform(get("/client/room/v1/room")
                .param("start", start)
                .param("end", end)
        ).andDo(print());

        // then(검증): 어떠한 결과가 나와야 한다.
        resultActions.andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.common").exists())
                .andExpect(jsonPath("$.common.createdAt").exists())
                .andExpect(jsonPath("$.common.status").exists())
                .andExpect(jsonPath("$.common.message").isArray())
                .andDo(result -> {
                    String jsonResponse = result.getResponse().getContentAsString();
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode rootNode = objectMapper.readTree(jsonResponse);

                    String status = rootNode.at("/common/status").asText();

                    assertThat(status).isEqualTo("fail");
                });
    }

    @Test
    public void 객실조회_시작일_보내지_않았을때() throws Exception {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String end = "2024-01-31T23:59:59.000Z";

        // when(실행): 어떠한 함수를 실행하면
        ResultActions resultActions = mvc.perform(get("/client/room/v1/room")
                .param("end", end)
        ).andDo(print());

        // then(검증): 어떠한 결과가 나와야 한다.
        resultActions.andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.common").exists())
                .andExpect(jsonPath("$.common.createdAt").exists())
                .andExpect(jsonPath("$.common.status").exists())
                .andExpect(jsonPath("$.common.message").isArray())
                .andDo(result -> {
                    String jsonResponse = result.getResponse().getContentAsString();
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode rootNode = objectMapper.readTree(jsonResponse);

                    String status = rootNode.at("/common/status").asText();
                    assertThat(status).isEqualTo("fail");
                });
    }
}