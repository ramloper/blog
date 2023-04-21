package rami.dev.ramiblog.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Slf4j
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder passwordEncoder(){ // 뭔지 공부
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{ // 이것도 공부
        // 1. CSRF 해제
        http.csrf().disable();

        // 2. frame option 해제 (시큐리티 h2-console 접속 허용을 위해)
        http.headers().frameOptions().disable();

        // 3. Form 로그인 설정
        http.formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login") // MyUserDetailsService가 호출, Post, x-www-~~
                .successHandler((request, response, authentication) -> {
                    log.debug("디버그 : 로그인 성공");
                })
                //entryPoint 인증과 권한 실패시 여기로 보냄
                .failureHandler((request, response, exception) -> {
                    log.debug("디버그 : 로그인 실패 : "+exception.getMessage());
                    response.sendRedirect("/loginForm");
                });

        // 4. 인증, 권한 필터 설정          주소가 S로 시작하면 다 못들어감(로그인안됐을경우)
        http.authorizeRequests(authorize -> authorize.antMatchers("/s/**").authenticated()
                .anyRequest().permitAll());

        return http.build();
    }
}
