package com.example.cusCom.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/CusCom/test")
class TestPageController {

    @GetMapping("/main")
    fun testMainPage():String{
        return "fragments2/main"
    }

    @GetMapping("/sharePlace")
    fun testSharePlacePage():String{
        return "fragments2/sharePlace"
    }

    @GetMapping("/login")
    fun testLogInPage():String{
        return "fragments2/login"
    }

    @GetMapping("/dataInput")
    fun testDataInputPage():String{
        return "fragments2/join"
    }

    @GetMapping("/admin")
    fun testAdminPage():String{
        return "fragments2/adminPartsList"
    }
}