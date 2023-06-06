package com.example.cusCom.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    @GetMapping("/test")
    fun printTestString() = "이 글이 출력되면 성공"
}