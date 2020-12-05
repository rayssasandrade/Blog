package com.spring.codeblog.utils;

import com.spring.codeblog.model.LoginDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Authorization {

    private static final String TOKEN_SECRET = "4a1a71a9ebb86dad178c14efb6wed2";
    private static final String TOKEN_BEARER = "Bearer ";

    private static final BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();

    private Authorization() {
    }

    public static String createToken(Claims claims) {
        final Instant tokenExpirationTime = Instant.now().plus(1440, ChronoUnit.MINUTES);
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(tokenExpirationTime))
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET)
                .compact();
    }

    public static Claims decodeToken(final String authHeader) throws IllegalAccessException {
        if (authHeader == null || !authHeader.startsWith(TOKEN_BEARER)) {
            throw new IllegalAccessException("Bad Authorization");
        }
        final String token = authHeader.split(" ")[1];
        return Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token).getBody();
    }

    public static String bcryptEncode(String data) {
        return pwdEncoder.encode(data);
    }

    public static Boolean bcryptMatch(String data, String encoded) {
        return pwdEncoder.matches(data, encoded);
    }

    public static String generateToken(LoginDto loginDto) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("ROLE_ADMIN", loginDto);
        return Authorization.createToken(Jwts.claims(claims));
    }
}