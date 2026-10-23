package io.github.kl.planning_api.domain.users;

import io.github.kl.planning_api.common.exceptions.UnauthorizedException;
import io.github.kl.planning_api.common.exceptions.ValidationException;
import io.github.kl.planning_api.common.validation.InvalidFields;
import io.github.kl.planning_api.domain.users.dto.AuthResponse;
import io.github.kl.planning_api.domain.users.dto.LoginForm;
import io.github.kl.planning_api.domain.users.dto.UsersRegisterForm;
import io.github.kl.planning_api.domain.users.model.UserEntity;
import io.github.kl.planning_api.infrastructure.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JwtService jwtService;

    public AuthResponse register(UsersRegisterForm form) {
        if(usersRepository.existsByLogin(form.login())) {
            throw new ValidationException(
                    List.of(new InvalidFields("login", "Login já cadastrado")));
        }

        String encryptedPassword = encoder.encode(form.password());

        UserEntity user = new UserEntity();
        user.setName(form.name());
        user.setLogin(form.login());
        user.setPassword(encryptedPassword);

        usersRepository.save(user);

        return toAuthResponse(user);
    }

    public AuthResponse authenticate(LoginForm form) {
        final String message = "Login ou senha invalidos";

        UserEntity user = usersRepository.findByLogin(form.login())
                .orElseThrow(() -> new UnauthorizedException(message));

        boolean passwordsMatch = encoder.matches(form.password(), user.getPassword());

        if(!passwordsMatch) {
            throw new UnauthorizedException(message);
        }

        return toAuthResponse(user);
    }

    private AuthResponse toAuthResponse(UserEntity user) {
        var token = jwtService.generationToken(user);
        var expiresIn = jwtService.getExpirationSeconds();
        return new AuthResponse(
                token,
                "Bearer",
                expiresIn,
                user.getName()
        );
    }
}
