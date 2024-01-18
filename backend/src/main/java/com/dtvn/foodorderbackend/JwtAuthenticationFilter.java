package com.dtvn.foodorderbackend;

import com.dtvn.foodorderbackend.model.entity.User;
import com.dtvn.foodorderbackend.service.JwtService;
import com.dtvn.foodorderbackend.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    final PathMatcher pathMatcher;
    final JwtService jwtService;
    final UserService userService;

    public static final String[] WHITE_LIST = {
            "/test",
            "/api/v1/auth/**",
            "/api/v2/auth/**",
            "/api/v1/auth/login",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/admin/export/get-bills"
    };

    @Override
    protected void doFilterInternal(@NonNull @RequestHeader(name = "Authorization") HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) {
        try {
            String auth = request.getHeader("Authorization");
            if (auth == null) {
                reject(response, new Throwable("This request " + request.hashCode() + " header: Authorization doesn't look like Bearer Token"));
                return;
            }
            String jwtToken = auth.substring(7);
            User user = jwtService.checkValid(jwtToken);
            if (user == null) {
                reject(response, new Throwable("TOKEN NOT VALID"));
                return;
            }

            if (request.getRequestURI().startsWith("/admin") && !user.getAuthorities().contains(User.ADMIN)) {
                reject(response, new Throwable("Bạn không phải admin"));
                return;
            }


            request.setAttribute("email", user.getEmail());
            request.setAttribute("user_id", jwtService.extractUserId(jwtToken));

            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Key, Authorization");
            try {
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                logger.error("{}", ExceptionUtils.getStackTrace(e));
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            reject(response, e);
            logger.error("{}", ExceptionUtils.getStackTrace(e));
        }
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        var accept = Arrays.stream(WHITE_LIST)
                .anyMatch(p -> pathMatcher.match(p, request.getServletPath()));
        if (accept) {
            logger.info("request bypass {},{}", request.getServletPath(), request.hashCode());
        }
        return accept;
    }

    private void reject(HttpServletResponse response, Throwable t) {
        try {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(t.getMessage());
            logger.error("{}", t.getMessage());
        } catch (Exception e) {
            logger.error("{}", ExceptionUtils.getStackTrace(e));
        }
    }
}
