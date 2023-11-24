package com.miracle.userservice.filter;

import com.miracle.userservice.util.Const;
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

        String sessionId = request.getHeader(Const.RequestHeader.SESSION_ID);
        if (Objects.isNull(sessionId)) {
            response.sendRedirect("/error/invalid-request");
            return;
        }

        int hashCode = request.getIntHeader(Const.RequestHeader.MIRACLE);
        String key = sessionId + PRIVATE_KEY;
        int h = key.hashCode();

        if (h != hashCode) {
            response.sendRedirect("/error/invalid-request");
            return;
        }

        chain.doFilter(servletRequest, servletResponse);
    }
}
