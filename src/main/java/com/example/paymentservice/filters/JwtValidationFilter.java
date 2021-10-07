package com.example.paymentservice.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class JwtValidationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        SecretKey secretKey = Keys.hmacShaKeyFor("1234FUdhiuhi4253uqhhczsf242qfsdz".getBytes(StandardCharsets.UTF_8));
        String header = httpServletRequest.getHeader("Authorization");
       if(header != null) {
           String jwt = header.split(" ")[1].trim();
           try {
               Claims claims = Jwts.parserBuilder()
                       .setSigningKey(secretKey)
                       .build()
                       .parseClaimsJws(jwt)
                       .getBody();
               String authorities = (String) claims.get("authorities");
               String username = (String) claims.get("username");
               Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, this.mapStringToAuthorities(authorities));
               SecurityContextHolder.getContext().setAuthentication(authentication);
           } catch (Exception e) {
               e.printStackTrace();
               throw new BadCredentialsException("Invalid token");
           }
       }
       filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private Collection<? extends GrantedAuthority> mapStringToAuthorities(String authorities) {
        if(authorities == null || authorities.equals("")) {
            return new ArrayList<>();
        }
        return Arrays.stream(authorities.split(" "))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().contains("/login");
    }
}
