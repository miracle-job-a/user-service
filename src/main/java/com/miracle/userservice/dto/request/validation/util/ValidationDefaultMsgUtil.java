package com.miracle.userservice.dto.request.validation.util;

public class ValidationDefaultMsgUtil {

    public static abstract class UserLogin {
        public static final String EMAIL = "400_1:이메일은 값이 입력되어야 합니다.";
        public static final String PASSWORD = "400_2:비밀번호는 값이 입력되어야 합니다.";
    }
}
