package com.miracle.userservice.util;

import com.miracle.userservice.exception.InvalidParameterException;

import java.time.LocalDate;

public abstract class ParameterValidator {

    /**
     * 페이징 기능을 구현하는 API에서 페이징 관련 파라미터를 검증하기 위한 메서드
     *
     * @param startPage    시작 페이지
     * @param endPage      끝 페이지
     * @param pageSize     페이지 당 콘텐츠의 개수
     * @param errorMessage 에러 메시지. '에러코드:메세지'로 표현할 것. ex) "400_1:시작 페이지는 양수여야 합니다."
     */
    public static void checkParameterWhenPaging(int startPage, int endPage, int pageSize, String errorMessage) {
        String[] split = errorMessage.split(":");
        String code = split[0];
        String message = split[1];

        checkPositive(startPage, code, message);
        checkPositive(endPage, code, message);
        checkPositive(pageSize, code, message);

        if (endPage < startPage) {
            throw new InvalidParameterException(code, message);
        }
    }

    private static void checkPositive(int i, String code, String errorMessage) {
        if (i <= 0) throw new InvalidParameterException(code, errorMessage);
    }

    /**
     * Enum 파라미터를 검증하기 위한 메서드
     *
     * @param <T>          열거 상수
     * @param enumClass    열거 상수 타입
     * @param enumName     열거 상수 이름
     * @param errorMessage 해당 상수가 존재하지 않는 경우에 나타날 에러 메시지
     * @return 해당 열거 상수
     * @author chocola
     */
    public static <T extends Enum<T>> T checkParameterEnum(Class<T> enumClass, String enumName, String errorMessage) {
        String[] split = errorMessage.split(":");
        String code = split[0];
        String message = split[1];

        try {
            return Enum.valueOf(enumClass, enumName);
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException(code, message);
        }
    }

    /**
     * 날짜 파라미터를 검증하기 위한 메서드. 1900-01-01는 기본 값이다.
     *
     * @param date 날짜
     * @return {@code date}가 유효할 경우 {@code date}를 반환. 그렇지 않을 경우 현재 날짜를 반환
     * @author chocola
     */
    public static LocalDate checkParameterLocalDate(LocalDate date) {
        if (date == null || date.isEqual(LocalDate.of(1900, 1, 1))) {
            return LocalDate.now();
        }

        return date;
    }
}
