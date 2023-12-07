package com.miracle.userservice.filter;

import com.miracle.userservice.util.Const;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Order(1)
@WebFilter("/v1/*")
public class RequestValidFilter implements Filter {

    private final String privateKey;

    public RequestValidFilter(Environment environment) {
        this.privateKey = environment.getProperty("my.private-key");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String sessionId = request.getHeader(Const.RequestHeader.SESSION_ID);
        if (Objects.isNull(sessionId)) {
            response.sendRedirect("/error/invalid-request");
            return;
        }

        int hashCode = request.getIntHeader(Const.RequestHeader.MIRACLE);
        String key = sessionId + privateKey;
        int h = key.hashCode();

        if (h != hashCode) {
            response.sendRedirect("/error/invalid-request");
            return;
        }

        chain.doFilter(servletRequest, servletResponse);
    }
}
