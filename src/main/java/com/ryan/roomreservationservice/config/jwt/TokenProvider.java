package com.ryan.roomreservationservice.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class TokenProvider {
    private final String secretKey;
    private final long tokenValidityInSeconds;

    public TokenProvider(
            @Value("${jwt.secretKey}") String secretKey,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds
    ) {
        this.secretKey = secretKey;
        this.tokenValidityInSeconds = tokenValidityInSeconds * 1000;
    }

    public String createToken(String userSpecification) {
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInSeconds);

        return Jwts.builder()
                .signWith(new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName()))
                .setSubject(userSpecification)
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
                .setExpiration(validity)
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
