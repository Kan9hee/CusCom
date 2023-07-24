package com.example.cusCom.controller

import com.example.cusCom.provideContent.service.DesktopPartsService
import com.example.cusCom.provideContent.service.EstimateDeserializer
import com.example.cusCom.userEstimate.dto.Estimate
import com.example.cusCom.userEstimate.service.EstimateService
import com.google.gson.GsonBuilder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.io.UnsupportedEncodingException

@Controller
class SharePlaceController(private val desktopPartsService: DesktopPartsService,
                           private val estimateService: EstimateService) {
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
}