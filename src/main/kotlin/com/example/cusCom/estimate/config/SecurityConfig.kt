package com.example.cusCom.estimate.config

import jakarta.servlet.DispatcherType
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

@Configuration
class SecurityConfig {

    @Bean
    fun filterChain(http:HttpSecurity):SecurityFilterChain{
        http.csrf{
            it.disable().authorizeHttpRequests{ authorize -> authorize
                .requestMatchers("/test","/CusCom/join").permitAll()
                .anyRequest().authenticated()
            }
        }
        .formLogin { login->login
            .loginPage("/CusCom/login")
            .loginProcessingUrl("/CusCom/login")
            .usernameParameter("userid")
            .passwordParameter("pw")
            .defaultSuccessUrl("/main",true)
            .permitAll()
        }.logout(withDefaults())

        return http.build()
    }
}