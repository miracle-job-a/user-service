package com.miracle.userservice.service;

import com.miracle.userservice.dto.request.UserJoinRequestDto;
import com.miracle.userservice.entity.User;
import com.miracle.userservice.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("UserService")
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @DisplayName("회원 가입 기능 성공 테스트")
    @Test
    void join_success() {
        //g
        String email = "abcd@aaa.com";
        String name = "쇼콜라";
        String password = "q!w@e#r$";
        String phone = "01029976276";
        String address = "서울시 서초구";
        LocalDate birth = LocalDate.parse("1935-02-19");

        UserJoinRequestDto input = UserJoinRequestDto.builder()
                .email(email)
                .name(name)
                .password(password)
                .phone(phone)
                .address(address)
                .birth(birth)
                .build();

        User entity = new User(email, password, name, phone, birth, address);

        //w
        userService.join(input);

        //t
        Mockito.verify(userRepository).save(entity);
    }

    @DisplayName("회원 가입 기능 실패 테스트_dto is null")
    @Test
    void join_fail() {
        assertThatThrownBy(() -> userService.join(null))
                .isInstanceOf(NullPointerException.class);
    }
}
