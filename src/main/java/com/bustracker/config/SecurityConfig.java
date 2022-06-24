package com.bustracker.config;

import com.bustracker.config.auth.AuthenticationProviderImpl;
import com.bustracker.service.UserService;
import com.bustracker.status.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.annotation.Resource;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Resource
    private AuthenticationProviderImpl authenticationProvider;

    @Resource
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/bus-logs/")
//                .permitAll()
                .hasRole(UserRole.BUS_DRIVER.getValue())
            .and()
                .formLogin()
                .loginPage("/")
            .and()
                .logout()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
            .and()
                .exceptionHandling()
            .and().formLogin().disable();


        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/assets/**", "/h2-console/**","/api/hello2");
    }

}
