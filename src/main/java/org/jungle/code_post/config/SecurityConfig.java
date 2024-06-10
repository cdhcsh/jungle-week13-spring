package org.jungle.code_post.config;


import org.jungle.code_post.auth.ExceptionHandlerFilter;
import org.jungle.code_post.auth.jwt.CustomAccessDeniedHandler;
import org.jungle.code_post.auth.jwt.CustomAuthenticationEntryPoint;
import org.jungle.code_post.auth.jwt.JwtTokenProvider;
import org.jungle.code_post.auth.jwt.JwtAuthFilter;
import org.jungle.code_post.auth.service.AuthDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthDetailService authDetailService;
    @Autowired
    private CustomAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private ExceptionHandlerFilter exceptionHandlerFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(CsrfConfigurer<HttpSecurity>::disable);
        http.cors(CorsConfigurer<HttpSecurity>::disable);

        http.sessionManagement((sm)->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.formLogin(FormLoginConfigurer<HttpSecurity>::disable);
        http.httpBasic(HttpBasicConfigurer<HttpSecurity>::disable);


        http.addFilterBefore(new JwtAuthFilter(jwtTokenProvider,authDetailService), UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling((exceptionHandling)->exceptionHandling
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler));
        http.addFilterBefore(exceptionHandlerFilter, JwtAuthFilter.class);


        http.authorizeHttpRequests((auth)-> auth.anyRequest().permitAll());
        return http.build();
    }
}
