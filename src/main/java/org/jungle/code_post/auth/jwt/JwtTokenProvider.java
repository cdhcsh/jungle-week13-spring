package org.jungle.code_post.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.jungle.code_post.member.dto.MemberInfoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    public static final String BEARER_TYPE = "Bearer";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String REFRESH_HEADER = "Refresh";
    public static final String BEARER_PREFIX = "Bearer ";

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access-token-expiration-time}")
    private long accessTokenExpirationTime;

    private Key key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(MemberInfoDTO memberInfoDTO){
        long now = new Date(System.currentTimeMillis()).getTime();


        Claims claims = Jwts.claims();
                claims.put("username",memberInfoDTO.getUsername());
                claims.put("role",memberInfoDTO.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + accessTokenExpirationTime))
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }

}
