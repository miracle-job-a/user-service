package com.miracle.userservice.interceptor;

import com.miracle.userservice.entity.User;
import com.miracle.userservice.exception.NoSuchUserException;
import com.miracle.userservice.exception.UserIdMismatchException;
import com.miracle.userservice.repository.UserRepository;
import com.miracle.userservice.util.Const;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserIdCheckInterceptor implements HandlerInterceptor {

    private final UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userIdHeader = request.getHeader(Const.RequestHeader.USER_ID);

        String pattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        AntPathMatcher pathMatcher = new AntPathMatcher();
        Map<String, String> variables = pathMatcher.extractUriTemplateVariables(pattern, path);

        String userIdStr = variables.get("userId");

        if (!Objects.equals(userIdStr, userIdHeader)) {
            throw new UserIdMismatchException("허가되지 않는 요청입니다.");
        }

        long userId = Long.parseLong(userIdStr);
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new NoSuchUserException("400", "해당 유저를 찾을 수 없습니다.");
        }

        return true;
    }
}
