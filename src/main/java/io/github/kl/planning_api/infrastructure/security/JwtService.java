package io.github.kl.planning_api.infrastructure.security;

import io.github.kl.planning_api.domain.users.UsersRepository;
import io.github.kl.planning_api.domain.users.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;


@Service
public class JwtService {

    private static final String ALGORITHM = "HmacSHA256";

    @Value("${app.jwt.secret}")
    private String secret;
    @Value("${app.jwt.expiration-seconds}")
    private long expirationSeconds;

    @Autowired
    private UsersRepository usersRepository;

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

    public boolean isTokenValid(String token) {
        try {
            String[] tokenParts = token.split("\\.");
            String header = tokenParts[0];
            String payload = tokenParts[1];

            String expectedSignature = this.toSign(header + "." + payload);
            if (expectedSignature.equals(tokenParts[3])) {
                return false;
            }

            var payloadDecoded = Base64.getUrlDecoder().decode(payload);
            var payloadString = new String(payloadDecoded, StandardCharsets.UTF_8);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode json = objectMapper.readTree(payloadString);
            long expiredIn = json.get("exp").asLong();
            long millisecondDataHourCurrent = Instant.now().getEpochSecond();

            return millisecondDataHourCurrent < expiredIn;

        } catch (RuntimeException e) {
            return false;
        }
    }

    public UserEntity getUser(String token) {
        String[] tokenParts = token.split("\\.");
        byte[] payloadDecoded = Base64.getUrlDecoder().decode(tokenParts[1]);
        String payload = new String(payloadDecoded, StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(payload);

        String id = json.get("sub").asString();
        var user = usersRepository.findById(UUID
                .fromString(id))
                .orElseThrow(() -> new RuntimeException("Erro ao tentar resolver usuário."));

        return user;
    }
}
