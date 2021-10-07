package com.example.paymentservice.filters;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class RequestValidationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Basic")) {
            header = header.trim();
            try {
                byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
                byte[] decode = Base64.getDecoder().decode(base64Token);
                String token = new String(decode, Charset.defaultCharset());
                int delim = token.indexOf(":");
                if(delim == -1) {
                    throw new BadCredentialsException("Invalid credentails");
                }
                String username = token.substring(0, delim);
                if(username.contains("test")) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
            } catch (IllegalArgumentException e) {
                throw new BadCredentialsException("Cannot decode token");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
