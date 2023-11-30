package com.miracle.userservice.util;

import com.miracle.userservice.dto.request.validation.util.ValidationDefaultMsgUtil;
import com.miracle.userservice.exception.InvalidParameterException;

public abstract class ParameterValidator {

    public static void checkParameterWhenPaging(int startPage, int endPage, int pageSize) {
        checkPositive(startPage);
        checkPositive(endPage);
        checkPositive(pageSize);

        if (endPage < startPage) throw new InvalidParameterException("400_2", ValidationDefaultMsgUtil.UserList.INVERSION);
    }

    private static void checkPositive(int i) {
        if (i <= 0) throw new InvalidParameterException("400_1", ValidationDefaultMsgUtil.UserList.POSITIVE);
    }
}
