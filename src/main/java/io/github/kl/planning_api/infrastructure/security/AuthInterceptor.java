package io.github.kl.planning_api.infrastructure.security;

import io.github.kl.planning_api.domain.users.model.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String autorization = request.getHeader("Authorization");
        if(autorization == null || !autorization.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        String token = autorization.substring(7);

        if(!jwtService.isTokenValid(token)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        UserEntity user = jwtService.getUser(token);
        request.setAttribute("userAuthenticated", user);

        return true;
    }
}
