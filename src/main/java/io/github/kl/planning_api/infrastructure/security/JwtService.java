package io.github.kl.planning_api.infrastructure.security;

import io.github.kl.planning_api.domain.users.model.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;


@Service
public class JwtService {

    private static final String ALGORITHM = "HmacsSHA256";

    @Value("${app.jwt.secret}")
    private String secret;
    @Value("${app.jwt.expiration-seconds}")
    private long expirationSeconds;

    public long getExpirationSeconds() {
        return expirationSeconds;
    }

    public String generationToken(UserEntity user) {
        long expiration = Instant.now().plusSeconds(expirationSeconds).getEpochSecond();
        String header = base64Url("""
                {"alg": "HS256", "typ": "JWT"}
                """.formatted(user.getId().toString(), user.getName(), expiration));
        String payload = base64Url("""
                { "sub": "%s", "name": "%s", "exp": %d }
                """);
        String signature = toSign(header + "." + payload);

        return header + "." + payload + "." + signature;
    }

    private String toSign(String content) {
        try {
            Mac mac = Mac.getInstance(ALGORITHM);
            byte[] bytesSecret = secret.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec privateKey = new SecretKeySpec(bytesSecret, ALGORITHM);
            mac.init(privateKey);
            byte[] publicKey = mac.doFinal(content.getBytes(StandardCharsets.UTF_8));

            return Base64.getUrlEncoder()
                    .withoutPadding()
                    .encodeToString(publicKey);
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao gerar JWT.", e);
        }
    }

    private  String base64Url(String value) {
        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }
}
