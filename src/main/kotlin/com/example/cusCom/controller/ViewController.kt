package com.example.cusCom.controller

import com.example.cusCom.exception.EstimateException
import com.example.cusCom.provideContent.service.DesktopPartsService
import com.example.cusCom.userEstimate.dto.Estimate
import com.example.cusCom.userEstimate.service.EstimateService
import com.example.cusCom.userEstimate.service.SharePlaceService
import org.bson.types.ObjectId
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/CusCom")
class ViewController(private val desktopPartsService: DesktopPartsService,
                     private val estimateService: EstimateService,
                     private val sharePlaceService: SharePlaceService) {

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

    @GetMapping("/estimatePage")
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
        return "partsListUser"
    }

    @GetMapping("/uploadPostPage")
    fun getUploadPage(@RequestParam("id") estimateID: ObjectId, model: Model):String{
        model.addAttribute("Estimate",estimateService.getUserEstimateById(estimateID))
        return "createPost"
    }

    @GetMapping("/sharePlace")
    fun testSharePlacePage(model: Model):String{
        model.addAttribute("postList",sharePlaceService.getPostList())
        return "sharePlace"
    }

    @GetMapping("/SharePlace/post")
    fun getPostPage(@RequestParam("id") postID:ObjectId,model: Model):String{
        val post=sharePlaceService.getPost("_id",postID.toHexString())
        model.addAttribute("post",post)
        model.addAttribute("postEstimate",estimateService.getUserEstimateById(ObjectId(post!!.estimateID)))
        model.addAttribute("commentList",sharePlaceService.getCommentList("postID",postID.toHexString()))
        return "viewPost"
    }

    private fun validateEstimate(estimate: Estimate) {
        if (estimateService.checkEstimateEmptyElement(estimate))
            throw EstimateException("견적사항에 결정되지 않은 요소가 있습니다.")
    }
}