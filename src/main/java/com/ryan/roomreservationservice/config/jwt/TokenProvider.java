package com.ryan.roomreservationservice.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class TokenProvider {
    private final String secretKey;
    private final long tokenExpirationTime;

    public TokenProvider(
            @Value("${jwt.secretKey}") String secretKey,
            @Value("${jwt.token-expiration-time}") long tokenExpirationTime
    ) {
        this.secretKey = secretKey;
        this.tokenExpirationTime = tokenExpirationTime * 1000;
    }

    public String createToken(String userSpecification) {
        return Jwts.builder()
                .signWith(new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName()))
                .setSubject(userSpecification)
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
                .setExpiration(Date.from(Instant.now().plus(tokenExpirationTime, ChronoUnit.HOURS)))
                .compact();
    }

    public String validateTokenAndGetSubject(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
