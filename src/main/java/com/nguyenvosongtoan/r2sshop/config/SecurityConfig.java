package com.nguyenvosongtoan.r2sshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   UserDetailsService userDetailsService, JwtAuthFilter filter) throws Exception {
        return httpSecurity
                .csrf().disable() // Vô hiệu hóa CSRF trong cấu hình bảo mật
                .authorizeHttpRequests() // Bắt đầu cấu hình xác thực các yêu cầu HTTP
                    .requestMatchers("/api/users", "/api/login").permitAll() // Cho phép truy cập không xác thực vào các API "/api/users" và "/api/login"
                    .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll() // Cho phép truy cập không xác thực vào các API GET bắt đầu bằng "/api/products/"
                    .requestMatchers(HttpMethod.GET, "/api/variantproducts/product/**").permitAll() // Cho phép truy cập không xác thực vào các API GET bắt đầu bằng "/api/variantproducts/product/"
                .and()
                .authorizeHttpRequests() // Tiếp tục cấu hình xác thực các yêu cầu HTTP
                    .anyRequest().authenticated() // Yêu cầu xác thực cho tất cả các yêu cầu còn lại
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Cấu hình chế độ tạo phiên không trạng thái
                .and()
                .authenticationProvider(authenticationProvider(userDetailsService)) // Cấu hình AuthenticationProvider để xác thực người dùng
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class) // Thêm JwtAuthFilter trước UsernamePasswordAuthenticationFilter trong chuỗi bộ lọc
                .build();
    }
}
