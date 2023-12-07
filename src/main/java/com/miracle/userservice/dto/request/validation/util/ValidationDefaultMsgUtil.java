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
        public static final String GIT_LINK = "400_4:깃 주소 형식이 올바르지 않습니다.";
        public static final String PHOTO = "400_5:사진 경로가 너무 깁니다.";
        public static final String CAREER = "400_6:경력이 음수입니다.";
        public static final String COLLECTION = "400_7:Collection이 null입니다.";
    }

    public static abstract class CheckDuplicate {

        public static final String EMAIL = "400:이메일 형식이 올바르지 않습니다.";
    }

    public static abstract class CoverLetterPost {
        public static final String USER_ID = "400_1:유저 아이디는 양수만 가능합니다.";
        public static final String TITLE = "400_2:제목이 없거나 글자 제한 수를 초과했습니다.";
        public static final String QNA = "400_3:질문 및 답변이 없습니다.";
    }

    public static abstract class UserUpdateInfo {

        public static final String PASSWORD = "400_1:비밀번호 형식이 올바르지 않습니다.";
        public static final String PHOTO = "400_2:사진 경로가 너무 깁니다.";
        public static final String COLLECTION = "400_3:Collection이 null입니다.";
        public static final String PHONE = "400_4:전화번호 형식이 올바르지 않습니다.";
        public static final String ADDRESS = "400_5:주소 형식이 올바르지 않습니다.";
    }

    public static abstract class ApplicationLetterPost {

        public static final String RESUME_ID = "400_1:이력서 아이디 값이 없거나 양수가 아닙니다.";
        public static final String COVER_LETTER_ID = "400_2:자기소개서 아이디 값이 없거나 양수가 아닙니다.";
        public static final String POST_TYPE = "400_3:공고 타입이 없거나 형식이 올바르지 않습니다.";
        public static final String POST_ID = "400_4:공고 아이디 값이 없거나 양수가 아닙니다.";
        public static final String SUBMIT_DATE = "400_5:지원 일자 값이 없습니다.";
        public static final String APPLICATION_STATUS = "400_6:지원 상태 값이 없거나 형식이 올바르지 않습니다.";
        public static final String USER_JOB = "400_7:유저 직무가 글자 제한 수를 초과했습니다.";
    }

    public static abstract class UserList {

        public static final String PAGING = "400_1:페이징 파라미터 형식이 올바르지 않습니다.";
    }

    public static abstract class ApplicantList {

        public static final String PAGING = "400_1:페이징 파라미터 형식이 올바르지 않습니다.";
        public static final String SORT = "400_2:정렬 파라미터 형식이 올바르지 않습니다. 값이 ('NAME', 'SUBMIT_DATE_ASC', 'SUBMIT_DATE_DESC') 중 하나여야 합니다.";
    }

    public static abstract class ApplicationLetterList {

        public static final String PAGING = "400_1:페이징 파라미터 형식이 올바르지 않습니다.";
        public static final String SORT = "400_2:정렬 기준이 올바르지 않습니다. 정렬 기준은 ('SUBMIT_DATE_ASC', 'SUBMIT_DATE_DESC') 안에 있는 값만 입력 가능합니다.";
    }

    public static abstract class UserJoinList {

        public static final String PAGING = "400:페이징 파라미터 형식이 올바르지 않습니다.";
    }

    public static abstract class CoverLetterList {

        public static final String PAGING = "400_1:페이징 파라미터 형식이 올바르지 않습니다.";
        public static final String SORT = "400_2:정렬 파라미터 형식이 올바르지 않습니다. 값이 ('MODIFIED_AT_ASC', 'MODIFIE_AT_DESC') 중 하나여야 합니다.";

    }

    public static abstract class InterviewPost {

        public static final String APPLICATION_LETTER_ID = "400_1:지원서 아이디 값이 없거나 양수가 아닙니다.";
        public static final String COLLECTION = "400_2:Collection이 null입니다.";
    }
}
