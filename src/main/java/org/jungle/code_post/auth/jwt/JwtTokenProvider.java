package org.jungle.code_post.auth.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.jungle.code_post.member.dto.MemberInfoDTO;
import org.jungle.code_post.member.entity.Member;
import org.jungle.code_post.member.service.MemberService;
import org.jungle.code_post.member.service.MemberserviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        log.info("key created");
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(MemberInfoDTO memberInfoDTO){
        long now = new Date(System.currentTimeMillis()).getTime();


        Claims claims = Jwts.claims();
                claims.put("id",memberInfoDTO.getId());
                claims.put("role",memberInfoDTO.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + accessTokenExpirationTime))
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }
    public String getId(String token){
        Claims claims = parseClaims(token);
        return claims.get("id",String.class);
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        }
        catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            log.info("Invaid JWT Token",e);
        }
        catch (ExpiredJwtException e){
            log.info("Expired JWT Token",e);
        }
        catch(UnsupportedJwtException e){
            log.info("Unsupported JWT Token",e);
        }
        catch (IllegalArgumentException e){
            log.info("JWT claims string is empty",e);
        }
        return false;
    }
    public Claims parseClaims(String accessToken){
        try{
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        }
        catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }

}
