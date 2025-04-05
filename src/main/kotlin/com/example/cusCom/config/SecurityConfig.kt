package com.example.cusCom.config

import com.example.cusCom.component.JwtComponent
import com.example.cusCom.enums.JwtWhitelistPath
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig {

    @Bean
    fun filterChain(http:HttpSecurity, jwtComponent:JwtComponent):SecurityFilterChain{
        http.csrf{ it.disable() }
            .authorizeHttpRequests{ authorize -> authorize
                .requestMatchers(*JwtWhitelistPath.allPatterns().toTypedArray()).permitAll()
                .requestMatchers("/CusCom/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            }
            .addFilterBefore(jwtComponent, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    @Bean
    fun passwordEncoder():PasswordEncoder{
        return BCryptPasswordEncoder()
    }
}