package com.example.cusCom.controller

import com.example.cusCom.provideContent.service.DesktopPartsService
import com.example.cusCom.userEstimate.exception.EstimateException
import com.example.cusCom.userEstimate.service.SharePlaceService
import org.bson.types.ObjectId
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/CusCom")
class ViewController(private val desktopPartsService: DesktopPartsService,
                     private val sharePlaceService: SharePlaceService) {

    @GetMapping("/login")
    fun loginPage():String{
        return "loginPage"
    }

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

    @GetMapping("/join")
    fun testDataInputPage():String{
        return "fragments2/join"
    }

    @GetMapping("/user")
    fun testUserPage(model: Model):String{
        model.addAttribute("userName", SecurityContextHolder.getContext().authentication.name)
        model.addAttribute("caseList",desktopPartsService.getCaseList())
        model.addAttribute("cpuCoolerList",desktopPartsService.getCpuCoolerList())
        model.addAttribute("cpuList",desktopPartsService.getCPUList())
        model.addAttribute("dataStorageList",desktopPartsService.getDataStorageList())
        model.addAttribute("graphicsCardList",desktopPartsService.getGraphicsCardList())
        model.addAttribute("memoryList",desktopPartsService.getMemoryList())
        model.addAttribute("motherBoardList",desktopPartsService.getMotherBoardList())
        model.addAttribute("powerSupplyList",desktopPartsService.getPowerSupplyList())
        return "fragments2/partsListUser"
    }

    @GetMapping("/postUploadPage")
    fun getUploadPage(@RequestParam("id") estimateID: ObjectId, model: Model):String{
        model.addAttribute("id",estimateID.toHexString())
        return "createPostPage"
    }

    @GetMapping("/SharePlace")
    fun getPostPage(@RequestParam("id") postID:ObjectId,model: Model):String{
        model.addAttribute("post",sharePlaceService.getPost("_id",postID.toHexString()))
        model.addAttribute("commentList",sharePlaceService.getCommentList("postID",postID.toHexString()))
        return "viewPostPage"
    }

    @ExceptionHandler(EstimateException::class)
    fun estimateException(ex: EstimateException, model: Model):String{
        model.addAttribute("errorMsg",ex.message)
        return "failView"
    }
}