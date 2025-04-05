package com.example.cusCom.config

import com.example.cusCom.component.JwtComponent
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig {
    @Bean
    fun disableJwtFilterRegistration(jwtComponent: JwtComponent): FilterRegistrationBean<JwtComponent> {
        val registration = FilterRegistrationBean(jwtComponent)
        registration.isEnabled = false
        return registration
    }
}