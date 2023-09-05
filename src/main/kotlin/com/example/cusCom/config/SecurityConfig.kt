package com.example.cusCom.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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
                .requestMatchers("/CusCom/estimatePage","/CusCom/joinPage","/CusCom/join").permitAll()
                .requestMatchers("/CusCom/adminPage/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            }
        }
        .formLogin { login->login
            .loginPage("/CusCom/loginPage")
            .loginProcessingUrl("/CusCom/loginPage")
            .usernameParameter("userid")
            .passwordParameter("pw")
            .defaultSuccessUrl("/CusCom/mainPage",true)
            .permitAll()
        }
        .logout{ logout->logout
            .logoutUrl("/CusCom/logout")
            .logoutSuccessUrl("/CusCom/login")
            .deleteCookies("JSESSIONID")
        }

        return http.build()
    }

    @Bean
    fun passwordEncoder():PasswordEncoder{
        return BCryptPasswordEncoder()
    }
}