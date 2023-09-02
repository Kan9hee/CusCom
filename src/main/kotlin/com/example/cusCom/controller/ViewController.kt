package com.example.cusCom.controller

import com.example.cusCom.provideContent.dto.SharePlacePost
import com.example.cusCom.provideContent.service.DesktopPartsService
import com.example.cusCom.provideContent.service.EstimateService
import com.example.cusCom.provideContent.service.SharePlaceService
import com.google.gson.Gson
import com.google.gson.JsonObject
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
                     private val sharePlaceService: SharePlaceService
) {

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
    fun testSharePlacePage(@RequestParam(defaultValue = "1") page: Int,
                           @RequestParam(defaultValue = "9") pageSize: Int,
                           @RequestParam(required = false) searchJson: String?,
                           model: Model):String{
        var postList:List<SharePlacePost> = if(searchJson!=null) {
            val temp= Gson().fromJson(searchJson,JsonObject::class.java)
            sharePlaceService.searchPost(temp.get("option").asString,temp.get("value").asString)
        } else {
            sharePlaceService.getPostList()
        }

        val listSize = postList.size
        val listStartIndex = (page - 1) * pageSize;
        val listEndIndex = kotlin.math.min(listStartIndex + pageSize, listSize);
        postList = postList.subList(listStartIndex,listEndIndex)

        model.addAttribute("postList",postList)
        model.addAttribute("totalPages",kotlin.math.ceil(postList.size.toDouble()/pageSize).toInt())
        model.addAttribute("currentPage",page)
        return "sharePlace"
    }

    @GetMapping("/SharePlace/post")
    fun getPostPage(@RequestParam("id") postID:ObjectId,model: Model):String{
        val post=sharePlaceService.loadPost("_id",postID.toHexString())
        model.addAttribute("post",post)
        model.addAttribute("postEstimate",estimateService.getUserEstimateById(ObjectId(post!!.estimateID)))
        model.addAttribute("commentList",sharePlaceService.getCommentList("postID",postID.toHexString()))
        return "viewPost"
    }
}