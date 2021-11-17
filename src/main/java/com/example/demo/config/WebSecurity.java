package com.example.demo.config;

import com.example.demo.jwt.JwtConfig;
import com.example.demo.jwt.JwtTokenVerifier;
import com.example.demo.jwt.JwtUsernamePasswordAuthentificationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.SecretKey;
import java.util.Arrays;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class  WebSecurity extends WebSecurityConfigurerAdapter {
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    @Value("${cors.allowed.origins}")
    private String[] allowedOrigins;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernamePasswordAuthentificationFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(jwtConfig, secretKey),JwtUsernamePasswordAuthentificationFilter.class)
                .authorizeRequests()
                .antMatchers("/login.html", "index", "image/*", "js/*").permitAll()
                .anyRequest()
                .authenticated();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(allowedOrigins));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
