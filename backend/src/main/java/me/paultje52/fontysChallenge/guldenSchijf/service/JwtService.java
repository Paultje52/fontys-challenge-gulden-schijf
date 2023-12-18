package me.paultje52.fontysChallenge.guldenSchijf.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.key}")
    private String jsonWebTokenKey;

    private Key getSigningKey() {
        byte[] keyBytes = this.jsonWebTokenKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generate(String userId) {
        JwtBuilder jwt = Jwts.builder();
        jwt.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60));
        jwt.claim("userId", userId);
        return jwt.signWith(this.getSigningKey()).compact();
    }

    public boolean verify(String jwt) {
        if (jwt == null || jwt.isEmpty()) return false;

        JwtParser builder = Jwts.parser().verifyWith((SecretKey) this.getSigningKey()).build();
        try {
            Date expiration = builder.parseSignedClaims(jwt).getPayload().getExpiration();
            return expiration.after(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

    public String getUserId(String jwt) {
        if (jwt == null || jwt.isEmpty()) return null;

        JwtParser builder = Jwts.parser().verifyWith((SecretKey) this.getSigningKey()).build();
        try {
            return builder.parseSignedClaims(jwt).getPayload().get("userId").toString();
        } catch (JwtException e) {
            return null;
        }
    }
}
