package com.example.paymentservice.filters;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtCreationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            SecretKey secretKey = Keys.hmacShaKeyFor("1234FUdhiuhi4253uqhhczsf242qfsdz".getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder().setIssuer("Docto")
                    .setIssuedAt(new Date())
                    .setSubject("user token")
                    .claim("username", authentication.getName())
                    .claim("authorities", this.mapAuthorities(authentication.getAuthorities()))
                    .signWith(secretKey)
                    .compact();
            httpServletResponse.setHeader("Authorization", jwt);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    private String mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> authorities1 = new HashSet<>();
        authorities.forEach(authority -> authorities1.add(authority.getAuthority()));
        return String.join(" ", authorities1);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }
 }
