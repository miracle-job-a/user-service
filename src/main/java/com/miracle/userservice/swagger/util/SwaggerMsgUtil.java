package com.miracle.userservice.swagger.util;

public abstract class SwaggerMsgUtil {

    public static abstract class ResponseCode {

        public static final String OK = "200";
        public static final String BAD_REQUEST = "400";
        public static final String BAD_REQUEST_1 = "400_1";
        public static final String BAD_REQUEST_2 = "400_2";
        public static final String UNAUTHORIZED = "401";
    }

    public static abstract class MediaType {

        public static final String APPLICATION_JSON = "application/json";
    }
}
