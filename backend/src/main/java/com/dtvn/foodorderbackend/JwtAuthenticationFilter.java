package com.dtvn.foodorderbackend;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    static Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    PathMatcher pathMatcher;

    @Autowired
    public void setPathMatcher(PathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }

    public static final String[] WHITE_LIST = {
            "/api/v1/auth/**"
    };

    @Override
    protected void doFilterInternal(HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) {
        String requestURI = request.getRequestURI();
        System.out.println("Prepare filter");
        String auth =request.getHeader("Authorization");
        System.out.println(auth);
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        return Arrays.stream(WHITE_LIST)
                .anyMatch(p -> pathMatcher.match(p, request.getServletPath()));
    }
}
