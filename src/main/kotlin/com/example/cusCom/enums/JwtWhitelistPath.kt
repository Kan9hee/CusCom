package com.example.cusCom.enums

enum class JwtWhitelistPath(val pattern: String) {
    LOGIN_PAGE("/CusCom/loginPage"),
    JOIN_PAGE("/CusCom/joinPage"),
    MAIN_PAGE("/CusCom/mainPage/**"),
    ESTIMATE_PAGE("/CusCom/estimatePage/**"),
    POST("/CusCom/post/**"),
    OPEN_API("/CusCom/API/open/**");

    companion object {
        fun allPatterns(): List<String> = values().map { it.pattern }
    }
}