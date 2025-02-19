package utils;

import java.time.Instant;
import java.time.ZonedDateTime;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class TestUtils {

    public static String objectToJson(Object object) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper()
                    .registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateToken(String subject, String secret) {

        Algorithm algorithm = Algorithm.HMAC256(secret);

        ZonedDateTime time = ZonedDateTime.now();

        Instant expiresAt = time.plusHours(4).toInstant();

        String token = JWT.create()
                .withIssuer("controle-financeiro")
                .withIssuedAt(time.toInstant())
                .withExpiresAt(expiresAt)
                .withSubject(subject)
                .sign(algorithm);

        return token;

    }

}
