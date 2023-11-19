package com.example.cusCom.controller

import com.example.cusCom.config.PagePathConfig
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/CusCom")
class ViewController(private val pagePathConfig: PagePathConfig) {

    @GetMapping("/loginPage")
    fun loginPage():String{
        if(SecurityContextHolder.getContext().authentication.name!="anonymousUser")
            return pagePathConfig.redirect

        val loginPath=pagePathConfig.normal[PagePathConfig.PathKey.LOGIN]
        return loginPath?:pagePathConfig.redirect
    }

    @GetMapping("/joinPage")
    fun testDataInputPage():String{
        val joinPath=pagePathConfig.normal[PagePathConfig.PathKey.JOIN]
        return joinPath?:pagePathConfig.redirect
    }

    @GetMapping("/mainPage")
    fun testMainPage():String{
        val mainPath=pagePathConfig.normal[PagePathConfig.PathKey.MAIN]
        return mainPath?:pagePathConfig.redirect
    }

    @GetMapping("/myPage")
    fun myPage():String{
        val myPathPath=pagePathConfig.normal[PagePathConfig.PathKey.MYPAGE]
        return myPathPath?:pagePathConfig.redirect
    }

    @GetMapping("/estimatePage")
    fun testUserPage():String{
        val estimatePath=pagePathConfig.normal[PagePathConfig.PathKey.ESTIMATE]
        return estimatePath?:pagePathConfig.redirect
    }

    @GetMapping("/uploadPostPage")
    fun getUploadPage():String{
        val uploadPostPath=pagePathConfig.normal[PagePathConfig.PathKey.UPLOADPOST]
        return uploadPostPath?:pagePathConfig.redirect
    }

    @GetMapping("/SharePlace")
    fun testSharePlacePage():String{
        val sharePlacePath=pagePathConfig.normal[PagePathConfig.PathKey.SHAREPLACE]
        return sharePlacePath?:pagePathConfig.redirect
    }

    @GetMapping("/SharePlace/post")
    fun getPostPage():String{
        val postPath=pagePathConfig.normal[PagePathConfig.PathKey.POST]
        return postPath?:pagePathConfig.redirect
    }
}