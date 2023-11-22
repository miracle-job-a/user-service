package com.miracle.userservice.dto.request.validation.util;

public class ValidationDefaultMsgUtil {

    public static abstract class UserLogin {
        public static final String EMAIL = "400_1:이메일은 값이 입력되어야 합니다.";
        public static final String PASSWORD = "400_2:비밀번호는 값이 입력되어야 합니다.";
    }

    public static abstract class UserJoin {

        public static final String EMAIL = "400_1:이메일 형식이 올바르지 않습니다.";
        public static final String NAME = "400_2:이름 형식이 올바르지 않습니다.";
        public static final String PASSWORD = "400_3:비밀번호 형식이 올바르지 않습니다.";
        public static final String PHONE = "400_4:전화번호 형식이 올바르지 않습니다.";
        public static final String BIRTH = "400_5:생년월일 형식이 올바르지 않습니다.";
        public static final String ADDRESS = "400_6:주소 형식이 올바르지 않습니다.";
    }

    public static abstract class ResumePost {

        public static final String USER_ID = "400_1:유저 아이디는 양수만 가능합니다.";
        public static final String TITLE = "400_2:제목이 없거나 너무 깁니다.";
        public static final String EDUCATION = "400_3:최종 학력이 너무 깁니다.";
        public static final String GIT_LINK = "400_4:깃 주소가 너무 깁니다.";
        public static final String PHOTO = "400_5:사진 경로가 너무 깁니다.";
        public static final String CAREER = "400_6:경력이 음수입니다.";
        public static final String COLLECTION = "400_7:Collection이 null입니다.";
    }
}
