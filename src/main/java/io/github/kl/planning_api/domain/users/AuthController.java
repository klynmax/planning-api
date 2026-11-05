package io.github.kl.planning_api.domain.users;

import io.github.kl.planning_api.domain.users.dto.AuthResponse;
import io.github.kl.planning_api.domain.users.dto.LoginForm;
import io.github.kl.planning_api.domain.users.dto.UsersRegisterForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public AuthResponse register(@RequestBody @Valid UsersRegisterForm form) {
        return  authService.register(form);
    }

    @PostMapping("/signin")
    public AuthResponse authenticate(@RequestBody @Valid LoginForm form) {
        return authService.authenticate(form);
    }
}
