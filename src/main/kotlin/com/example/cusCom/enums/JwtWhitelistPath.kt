package com.example.cusCom.enums

enum class JwtWhitelistPath(val pattern: String) {
    STATIC_JS("/js/**"),
    STATIC_CSS("/css/**"),
    STATIC_IMAGE("/images/**"),

    LOGIN_PAGE("/CusCom/loginPage/**"),
    JOIN_PAGE("/CusCom/joinPage/**"),
    MAIN_PAGE("/CusCom/mainPage"),
    SEARCH_RESULT_PAGE("/CusCom/mainPage/**"),
    MY_PAGE("/CusCom/myPage/**"),
    ESTIMATE_PAGE("/CusCom/estimatePage/**"),
    UPLOAD_POST_PAGE("/CusCom/uploadPostPage/**"),
    POST_PAGE("/CusCom/post/**"),

    ADMIN_PAGE("/CusCom/admin/**"),

    OPEN_API("/CusCom/API/open/**");

    companion object {
        fun allPatterns(): List<String> = values().map { it.pattern }
    }
}