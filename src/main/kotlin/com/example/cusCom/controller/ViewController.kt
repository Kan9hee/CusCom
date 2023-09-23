package com.example.cusCom.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/CusCom")
class ViewController() {

    @GetMapping("/loginPage")
    fun loginPage():String{
        return "login"
    }

    @GetMapping("/joinPage")
    fun testDataInputPage():String{
        return "join"
    }

    @GetMapping("/mainPage")
    fun testMainPage():String{
        return "main"
    }

    @GetMapping("/myPage")
    fun myPage():String{
        return "userPage"
    }

    @GetMapping("/estimatePage")
    fun testUserPage():String{
        return "partsListUser"
    }

    @GetMapping("/uploadPostPage")
    fun getUploadPage():String{
        return "createPost"
    }

    @GetMapping("/SharePlace")
    fun testSharePlacePage():String{
        return "sharePlace"
    }

    @GetMapping("/SharePlace/post")
    fun getPostPage():String{
        return "viewPost"
    }
}