package com.thanh.badminton_ranking.authentication.service;

import com.thanh.badminton_ranking.user.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(User user) {
        SecretKey key = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secret)
        );

        Date now = new Date();
        Date expiyDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getRole().name())
                .issuedAt(now)
                .expiration(expiyDate)
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token) {
        SecretKey key = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secret)
        );

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public  boolean validateToken(String token, User user) {
        String username = extractUsername(token);
        return username.equals(user.getUsername());
    }
}
