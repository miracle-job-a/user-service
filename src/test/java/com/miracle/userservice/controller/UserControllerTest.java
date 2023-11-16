package com.miracle.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miracle.userservice.dto.request.UserJoinRequestDto;
import com.miracle.userservice.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("UserController")
@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest
class UserControllerTest extends DummyData {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @MockBean
    private UserService userService;

    @DisplayName("회원 가입 성공 테스트")
    @Test
    void join_success() throws Exception {
        //g
        UserJoinRequestDto input = UserJoinRequestDto.builder()
                .email("abcd@aaa.com")
                .name("쇼콜라")
                .password("q!w@e#r$")
                .phone("01029976276")
                .address("서울시 서초구")
                .birth(LocalDate.parse("1935-02-19"))
                .build();

        //w
        mvc.perform(
                post("/v1/user/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(input))
                        .header("sessionId", sessionId)
                        .header("miracle", miracle))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.httpStatus").value(200))
                .andExpect(jsonPath("$.message").value("회원 가입 성공"))
                .andExpect(jsonPath("$.data").doesNotExist())
                .andDo(print());

        //t
        Mockito.verify(userService).join(input);
    }

    @DisplayName("회원 가입 실패 테스트_이메일 검증")
    @ParameterizedTest(name = "email={0}")
    @NullAndEmptySource
    @ValueSource(strings = {"    ", "chocola"})
    void join_fail_email(String email) throws Exception {
        //g
        UserJoinRequestDto input = UserJoinRequestDto.builder()
                .email(email)
                .name("쇼콜라")
                .password("q!w@e#r$")
                .phone("01029976276")
                .address("서울시 서초구")
                .birth(LocalDate.parse("1935-02-19"))
                .build();

        //w
        mvc.perform(
                post("/v1/user/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(input))
                        .header("sessionId", sessionId)
                        .header("miracle", miracle))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.httpStatus").value(400))
                .andExpect(jsonPath("$.code").value("400_1"))
                .andExpect(jsonPath("$.exception").value("MethodArgumentNotValidException"))
                .andDo(print());
    }

    @DisplayName("회원 가입 실패 테스트_이름 검증")
    @ParameterizedTest(name = "name={0}")
    @NullAndEmptySource
    @ValueSource(strings = {"    ", "chocola", "쇼"})
    void join_fail_name(String name) throws Exception {
        //g
        UserJoinRequestDto input = UserJoinRequestDto.builder()
                .email("chocola@ggibbiddori.com")
                .name(name)
                .password("q!w@e#r$")
                .phone("01029976276")
                .address("서울시 서초구")
                .birth(LocalDate.parse("1935-02-19"))
                .build();

        //w
        mvc.perform(
                        post("/v1/user/join")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(om.writeValueAsString(input))
                                .header("sessionId", sessionId)
                                .header("miracle", miracle))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.httpStatus").value(400))
                .andExpect(jsonPath("$.code").value("400_2"))
                .andExpect(jsonPath("$.exception").value("MethodArgumentNotValidException"))
                .andDo(print());
    }

    @DisplayName("회원 가입 실패 테스트_비밀번호 검증")
    @ParameterizedTest(name = "password={0}")
    @NullAndEmptySource
    @ValueSource(strings = {"    ", "cho!@", "chocolaggibbiddori"})
    void join_fail_password(String password) throws Exception {
        //g
        UserJoinRequestDto input = UserJoinRequestDto.builder()
                .email("chocola@ggibbiddori.com")
                .name("쇼콜라")
                .password(password)
                .phone("01029976276")
                .address("서울시 서초구")
                .birth(LocalDate.parse("1935-02-19"))
                .build();

        //w
        mvc.perform(
                        post("/v1/user/join")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(om.writeValueAsString(input))
                                .header("sessionId", sessionId)
                                .header("miracle", miracle))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.httpStatus").value(400))
                .andExpect(jsonPath("$.code").value("400_3"))
                .andExpect(jsonPath("$.exception").value("MethodArgumentNotValidException"))
                .andDo(print());
    }

    @DisplayName("회원 가입 실패 테스트_전화번호 검증")
    @ParameterizedTest(name = "phone={0}")
    @NullAndEmptySource
    @ValueSource(strings = {"    ", "010-1234-5678", "0112321424", "12345678"})
    void join_fail_phone(String phone) throws Exception {
        //g
        UserJoinRequestDto input = UserJoinRequestDto.builder()
                .email("chocola@ggibbiddori.com")
                .name("쇼콜라")
                .password("q!w@e#r$")
                .phone(phone)
                .address("서울시 서초구")
                .birth(LocalDate.parse("1935-02-19"))
                .build();

        //w
        mvc.perform(
                        post("/v1/user/join")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(om.writeValueAsString(input))
                                .header("sessionId", sessionId)
                                .header("miracle", miracle))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.httpStatus").value(400))
                .andExpect(jsonPath("$.code").value("400_4"))
                .andExpect(jsonPath("$.exception").value("MethodArgumentNotValidException"))
                .andDo(print());
    }

    @DisplayName("회원 가입 실패 테스트_생년월일 검증")
    @ParameterizedTest(name = "birth={0}")
    @MethodSource("provideBirths")
    void join_fail_birth(LocalDate birth) throws Exception {
        //g
        UserJoinRequestDto input = UserJoinRequestDto.builder()
                .email("chocola@ggibbiddori.com")
                .name("쇼콜라")
                .password("q!w@e#r$")
                .phone("01012345678")
                .address("서울시 서초구")
                .birth(birth)
                .build();

        //w
        mvc.perform(
                        post("/v1/user/join")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(om.writeValueAsString(input))
                                .header("sessionId", sessionId)
                                .header("miracle", miracle))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.httpStatus").value(400))
                .andExpect(jsonPath("$.code").value("400_5"))
                .andExpect(jsonPath("$.exception").value("MethodArgumentNotValidException"))
                .andDo(print());
    }

    @DisplayName("회원 가입 실패 테스트_주소 검증")
    @ParameterizedTest(name = "address={0}")
    @NullAndEmptySource
    @ValueSource(strings = "    ")
    void join_fail_address(String address) throws Exception {
        //g
        UserJoinRequestDto input = UserJoinRequestDto.builder()
                .email("chocola@ggibbiddori.com")
                .name("쇼콜라")
                .password("q!w@e#r$")
                .phone("01012345678")
                .address(address)
                .birth(LocalDate.parse("1993-01-01"))
                .build();

        //w
        mvc.perform(
                        post("/v1/user/join")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(om.writeValueAsString(input))
                                .header("sessionId", sessionId)
                                .header("miracle", miracle))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.httpStatus").value(400))
                .andExpect(jsonPath("$.code").value("400_6"))
                .andExpect(jsonPath("$.exception").value("MethodArgumentNotValidException"))
                .andDo(print());
    }
}
