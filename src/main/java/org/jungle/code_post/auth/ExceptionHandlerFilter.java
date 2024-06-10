package org.jungle.code_post.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jungle.code_post.common.dto.ApiResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Slf4j
@AllArgsConstructor
@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    private ObjectMapper mapper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        }
        catch (Exception e) {
            String responseBody = mapper.writeValueAsString(ApiResponseDTO.builder()
                    .code("failed")
                    .message("토큰이 유효하지 않습니다.")
                    .build());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(responseBody);
        }
    }
}
