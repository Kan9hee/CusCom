package com.example.cusCom.controller

import com.example.cusCom.provideContent.dto.User
import com.example.cusCom.provideContent.service.UserService
import com.example.cusCom.provideContent.dto.Comment
import com.example.cusCom.provideContent.dto.Estimate
import com.example.cusCom.provideContent.dto.SharePlacePost
import com.example.cusCom.provideContent.service.EstimateService
import com.example.cusCom.provideContent.service.SharePlaceService
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/CusCom")
class UserRestController(private val estimateService: EstimateService,
                         private val sharePlaceService: SharePlaceService,
                         private val userService: UserService) {

    @PostMapping("/join")
    fun postUserJoin(@RequestBody user: User): ResponseEntity<String> {
        userService.joinUser(user)
        return ResponseEntity.ok("Success")
    }

    @PostMapping("/estimate")
    fun postDataTest(@RequestParam("estimate") estimateJSON:String): ResponseEntity<String> {
        val estimateResult=Gson().fromJson(estimateJSON, Estimate::class.java)
        validateEstimate(estimateResult)
        estimateService.saveUserEstimate(estimateResult)
        return ResponseEntity.ok("Success")
    }

    @PostMapping("/uploadPost")
    fun uploadPost(@RequestParam("postData") postJSON:String): ResponseEntity<String> {
        sharePlaceService.uploadPost(Gson().fromJson(postJSON, SharePlacePost::class.java))
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

    private fun validateEstimate(estimate: Estimate) {
        val check = runCatching{
            estimateService.checkEstimateEmptyElement(estimate)
            estimateService.checkDesktopEstimate(estimate)
        }
        check.onFailure { ex -> throw ex }
    }
}