package com.miracle.userservice.filter;

import com.miracle.userservice.util.Const;
import org.springframework.core.env.Environment;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter("/v1/*")
public class RequestValidFilter extends HttpFilter {

    private final String privateKey;

    public RequestValidFilter(Environment environment) {
        this.privateKey = environment.getProperty("my.private-key");
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
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

        chain.doFilter(request, response);
    }
}
