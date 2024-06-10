package org.jungle.code_post.auth.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jungle.code_post.auth.jwt.JwtTokenProvider;
import org.jungle.code_post.auth.service.AuthDetailService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    private final AuthDetailService authDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(JwtTokenProvider.AUTHORIZATION_HEADER);
        if (authorizationHeader != null && authorizationHeader.startsWith(JwtTokenProvider.BEARER_PREFIX)) {
            String token = authorizationHeader.substring(JwtTokenProvider.BEARER_PREFIX.length());
            if (jwtTokenProvider.validateToken(token)) {
                String id = jwtTokenProvider.getId(token);
                UserDetails userDetails = authDetailService.loadUserByUsername(id);

                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
