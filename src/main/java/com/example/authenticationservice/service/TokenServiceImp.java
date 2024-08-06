package com.example.authenticationservice.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Date;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class TokenServiceImp implements TokenService {

    @Value("${auth.jwt.password}")
    private String secretKey;

    @Override
    public String generateToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        Instant now = Instant.now();
        Instant exp = now.plus(5, ChronoUnit.MINUTES);

        return JWT.create()
                .withIssuer("auth-service")
                .withAudience("bookmarket")
                .withSubject(username)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(exp))
                .sign(algorithm);
    }
}