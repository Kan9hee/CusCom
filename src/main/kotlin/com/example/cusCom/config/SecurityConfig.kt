package com.example.cusCom.config

import com.example.cusCom.component.JwtComponent
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
                .requestMatchers(
                    "/CusCom/estimatePage",
                    "/CusCom/SharePlace/**",
                    "/CusCom/API/open/**").permitAll()
                .requestMatchers("/CusCom/adminPage/**").hasRole("ADMIN")
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