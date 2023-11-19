package com.example.cusCom.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "path")
class PagePathConfig {
    lateinit var admin: PathConfig
    lateinit var normal: Map<PathKey, String>
    lateinit var redirect: String

    enum class PathKey { LOGIN, JOIN, MAIN, MYPAGE, ESTIMATE, UPLOADPOST, SHAREPLACE, POST }

    class PathConfig {
        lateinit var main: String
        lateinit var edit: Map<AdminPathKey, String>
        enum class AdminPathKey { CASE, CPU, CPUCOOLER, DATASTORAGE, GRAPHICSCARD, MEMORY, MOTHERBOARD, POWERSUPPLY }
    }
}