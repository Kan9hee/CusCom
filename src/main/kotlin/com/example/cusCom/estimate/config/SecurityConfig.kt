package com.example.cusCom.estimate.config

import jakarta.servlet.DispatcherType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun filterChain(http:HttpSecurity):SecurityFilterChain{
        http.authorizeHttpRequests { request -> request
                .dispatcherTypeMatchers(DispatcherType.FORWARD)
                .permitAll()
                .anyRequest()
                .authenticated()
            }
            .formLogin{login->login.defaultSuccessUrl("/main",true).permitAll()}
            .logout(withDefaults())

        return http.build()
    }
}