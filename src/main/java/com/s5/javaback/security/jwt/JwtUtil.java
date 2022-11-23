package com.s5.javaback.security.jwt;

import com.s5.javaback.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${iceCream}")
    private String key;
    private static final String BEARER_PART = "Bearer ";
    private static final String EMPTY = "";
    private static final String AUTHORITIES = "authorities";
    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
 /*   public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }*/
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    public boolean isToken(String token, UserDetails userDetails) {
        return getUsername(token).equals(userDetails.getUsername()) && !isExpired(token);
    }
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 20 * 1000 * 60))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return (getUsername(token).equals(userDetails.getUsername()) && !isExpired(token));
    }

    private String getToken(String authorizationHeader) {
        return authorizationHeader.replace(BEARER_PART, EMPTY);
    }

}
