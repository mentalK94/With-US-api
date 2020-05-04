package kr.co.mentalK94.withus.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JWTUtil {

    private Key key;

    public JWTUtil(String secretKey) {
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String crateToken(Long userId, String name) {

        JwtBuilder jwtBuilder = Jwts.builder()
                                    .claim("userId", userId)
                                    .claim("name", name);

        return jwtBuilder.signWith(key, SignatureAlgorithm.HS256).compact();

    }

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(key)
                .parseClaimsJws(token).getBody();
    }
}
