package com.bustracker.config;

import com.bustracker.config.auth.AuthenticationEntryPointImpl;
import com.bustracker.config.auth.AuthenticationProviderImpl;
import com.bustracker.config.auth.filter.AuthFilter;
import com.bustracker.service.UserService;
import com.bustracker.enums.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    private AuthenticationEntryPointImpl unauthorizedHandler;

    @Resource
    private AuthenticationProviderImpl authenticationProvider;

    @Resource
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthFilter authenticationFilter() {
        return new AuthFilter();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
//                .antMatchers("/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/bus-logs").permitAll()
//                .antMatchers(HttpMethod.GET, "/bus-logs").permitAll()
//                .antMatchers("/api/bus-logs").permitAll()
                .anyRequest().authenticated()
//                .hasRole(UserRole.BUS_DRIVER.getValue())
            .and()
                .formLogin()
                .loginPage("/")
            .and()
                .logout()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
            .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
            .and()
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin().disable();

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/assets/**", "/h2-console/**","/api/hello2");
    }

}
