package com.miracle.userservice.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Order(1)
@WebFilter("/v1/*")
public class RequestValidFilter implements Filter {

    private static final String PRIVATE_KEY = "TkwkdsladkdlrhdnjfrmqdhodlfjgrpaksgdlwnjTdjdy";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String sessionId = request.getHeader("sessionId");
        if (Objects.isNull(sessionId)) {
            response.sendRedirect("/error/invalid-request");
            return;
        }

        int hashCode = request.getIntHeader("miracle");
        String key = sessionId + PRIVATE_KEY;
        int h = key.hashCode();

        if (h != hashCode) {
            response.sendRedirect("/error/invalid-request");
            return;
        }

        chain.doFilter(servletRequest, servletResponse);
    }
}
