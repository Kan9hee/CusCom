package com.example.cusCom.controller

import com.example.cusCom.provideContent.dto.User
import com.example.cusCom.provideContent.service.DesktopPartsService
import com.example.cusCom.provideContent.service.UserService
import com.example.cusCom.userEstimate.dto.Comment
import com.example.cusCom.userEstimate.service.EstimateDeserializer
import com.example.cusCom.userEstimate.dto.Estimate
import com.example.cusCom.userEstimate.dto.SharePlacePost
import com.example.cusCom.userEstimate.service.EstimateService
import com.example.cusCom.userEstimate.service.SharePlaceService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.io.UnsupportedEncodingException

@RestController
@RequestMapping("/CusCom")
class UserRestController(private val desktopPartsService: DesktopPartsService,
                         private val estimateService: EstimateService,
                         private val sharePlaceService: SharePlaceService,
                         private val userService: UserService) {

    @PostMapping("/join")
    fun postUserJoin(@RequestBody user: User): ResponseEntity<String> {
        userService.joinUser(user)
        return ResponseEntity.ok("Success")
    }

    @PostMapping("/estimate")
    fun postDataTest(@RequestParam("estimate") estimateJSON:String): ResponseEntity<String> {
        try {
            val estimate= GsonBuilder()
                .registerTypeAdapter(Estimate::class.java, EstimateDeserializer(desktopPartsService))
                .create()
                .fromJson(estimateJSON, Estimate::class.java)
            estimateService.saveUserEstimate(estimate)
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return ResponseEntity.ok("Success")
    }

    @PostMapping("/uploadPost")
    fun uploadPost(@RequestParam("postData") postJSON:String): ResponseEntity<String> {
        sharePlaceService.uploadPost(Gson().fromJson(postJSON,SharePlacePost::class.java))
        return ResponseEntity.ok("Success")
    }

    @PostMapping("/uploadComment")
    fun uploadComment(@RequestParam("commentData") commentJSON:String): ResponseEntity<String> {
        val jsonObject=Gson().fromJson(commentJSON, JsonObject::class.java)
        jsonObject.addProperty("userName", SecurityContextHolder.getContext().authentication.name)
        sharePlaceService.uploadComment(Gson().fromJson(Gson().toJson(jsonObject), Comment::class.java))
        return ResponseEntity.ok("Success")
    }

    @PostMapping("/increaseLike")
    fun increaseLike(@RequestParam("likeAction") action:String): ResponseEntity<String> {
        return ResponseEntity.ok("Success")
    }

    @PostMapping("/decreaseLike")
    fun decreaseLike(@RequestParam("dislikeAction") action:String): ResponseEntity<String> {
        return ResponseEntity.ok("Success")
    }
}