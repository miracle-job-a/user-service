package com.miracle.userservice.interceptor;

import com.miracle.userservice.exception.UserIdMismatchException;
import com.miracle.userservice.util.Const;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@Component
public class UserIdCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userIdHeader = request.getHeader(Const.RequestHeader.USER_ID);

        String pattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        AntPathMatcher pathMatcher = new AntPathMatcher();
        Map<String, String> variables = pathMatcher.extractUriTemplateVariables(pattern, path);

        String userId = variables.get("userId");

        if (!Objects.equals(userId, userIdHeader)) {
            throw new UserIdMismatchException("허가되지 않는 요청입니다.");
        }

        return true;
    }
}
