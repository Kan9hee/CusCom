package com.example.cusCom.controller

import com.example.cusCom.provideContent.service.DesktopPartsService
import com.example.cusCom.userEstimate.service.EstimateDeserializer
import com.example.cusCom.userEstimate.dto.Estimate
import com.example.cusCom.userEstimate.dto.SharePlacePost
import com.example.cusCom.userEstimate.service.EstimateService
import com.example.cusCom.userEstimate.service.SharePlaceService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.bson.types.ObjectId
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.io.UnsupportedEncodingException

@Controller
class SharePlaceController(private val desktopPartsService: DesktopPartsService,
                           private val estimateService: EstimateService,
                           private val sharePlaceService: SharePlaceService) {

    @PostMapping("/estimate")
    fun postDataTest(@RequestParam("Estimates") estimateJSON:String):String{
        try {
            val estimate= GsonBuilder()
                .registerTypeAdapter(Estimate::class.java, EstimateDeserializer(desktopPartsService))
                .create()
                .fromJson(estimateJSON, Estimate::class.java)
            estimateService.saveUserEstimate(estimate)
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return "redirect:/main"
    }

    @GetMapping("/CusCom/postUploadPage")
    fun getUploadPage(@RequestParam("id") estimateID:ObjectId,model: Model):String{
        model.addAttribute("id",estimateID.toHexString())
        return "createPostPage"
    }

    @PostMapping("/CusCom/uploadPost")
    fun uploadPost(@RequestParam("postData") postJSON:String): ResponseEntity<String> {
        println(postJSON)
        sharePlaceService.uploadPost(Gson().fromJson(postJSON,SharePlacePost::class.java))
        return ResponseEntity.ok("Success")
    }

    @GetMapping("/CusCom/SharePlace")
    fun getPostPage(@RequestParam("id") postID:ObjectId,model: Model):String{
        model.addAttribute("post",sharePlaceService.getPost(postID.toHexString()))
        return "viewPostPage"
    }
}