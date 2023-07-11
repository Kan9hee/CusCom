package com.example.cusCom.estimate.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun filterChain(http:HttpSecurity):SecurityFilterChain{
        http.csrf{
            it.disable().authorizeHttpRequests{ authorize -> authorize
                .requestMatchers("/test","/CusCom/join").permitAll()
                .requestMatchers("/adminPage/main").hasRole("ADMIN")
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

    @Bean
    fun passwordEncoder():PasswordEncoder{
        return BCryptPasswordEncoder()
    }
}