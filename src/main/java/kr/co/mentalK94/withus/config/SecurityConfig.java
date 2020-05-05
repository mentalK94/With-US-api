package kr.co.mentalK94.withus.config;

import kr.co.mentalK94.withus.filters.JwtAuthenticationFilter;
import kr.co.mentalK94.withus.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.secret}") // application.properties에 입력한 값을 secretKey에 적용
    private String secretKey;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        Filter filter = new JwtAuthenticationFilter(authenticationManager(), jwtUtil());

        httpSecurity.formLogin().disable()
                    .cors().disable()
                    .csrf().disable()
                    .headers().frameOptions().disable()
                    .and()
                    .addFilter(filter)
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTUtil jwtUtil() {
        return new JWTUtil(secretKey);
    }

}
