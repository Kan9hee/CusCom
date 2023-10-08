package com.example.cusCom.controller

import com.example.cusCom.exception.EstimateException
import com.example.cusCom.provideContent.dto.User
import com.example.cusCom.provideContent.service.UserService
import com.example.cusCom.provideContent.dto.Comment
import com.example.cusCom.provideContent.dto.Estimate
import com.example.cusCom.provideContent.dto.SharePlacePost
import com.example.cusCom.provideContent.dto.parts.*
import com.example.cusCom.provideContent.service.DesktopPartsService
import com.example.cusCom.provideContent.service.EstimateService
import com.example.cusCom.provideContent.service.SharePlaceService
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.bson.types.ObjectId
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/CusCom/API")
class UserRestController(private val desktopPartsService: DesktopPartsService,
                         private val estimateService: EstimateService,
                         private val sharePlaceService: SharePlaceService,
                         private val userService: UserService) {

    @PostMapping("/join")
    fun postUserJoin(@RequestBody user: User): ResponseEntity<String> {
        userService.joinUser(user)
        return ResponseEntity.ok("Success")
    }

    @GetMapping("/checkLogin")
    fun checkLogin(): ResponseEntity<String> {
        val authentication=SecurityContextHolder.getContext().authentication
        return ResponseEntity.ok("{\"loggedIn\": ${authentication != null && authentication.isAuthenticated}}")
    }

    @GetMapping("/getUserEstimateList")
    fun userEstimateList(): List<Estimate> {
        val userName=SecurityContextHolder.getContext().authentication.name
        return estimateService.getUserEstimateList("userName",userName)
    }

    @GetMapping("/getUserEstimate")
    fun userEstimate(@RequestParam("id",required = false) estimateID: String?): Estimate {
        return estimateID?.let { estimateService.getUserEstimateById(ObjectId(it)) }?: Estimate()
    }

    @GetMapping("/caseList")
    @ResponseBody
    fun caseListApi(): List<Case> {
        return desktopPartsService.getCaseList()
    }

    @GetMapping("/cpuCoolerList")
    @ResponseBody
    fun cpuCoolerListApi(): List<CPUCooler> {
        return desktopPartsService.getCpuCoolerList()
    }

    @GetMapping("/cpuList")
    @ResponseBody
    fun cpuListApi(): List<CPU> {
        return desktopPartsService.getCPUList()
    }

    @GetMapping("/dataStorageList")
    @ResponseBody
    fun dataStorageListApi(): List<DataStorage> {
        return desktopPartsService.getDataStorageList()
    }

    @GetMapping("/graphicsCardList")
    @ResponseBody
    fun graphicsCardListApi(): List<GraphicsCard> {
        return desktopPartsService.getGraphicsCardList()
    }

    @GetMapping("/memoryList")
    @ResponseBody
    fun memoryListApi(): List<Memory> {
        return desktopPartsService.getMemoryList()
    }

    @GetMapping("/motherBoardList")
    @ResponseBody
    fun motherBoardListApi(): List<MotherBoard> {
        return desktopPartsService.getMotherBoardList()
    }

    @GetMapping("/powerSupplyList")
    @ResponseBody
    fun powerSupplyListApi(): List<PowerSupply> {
        return desktopPartsService.getPowerSupplyList()
    }

    @GetMapping("/searchCase")
    @ResponseBody
    fun searchCaseApi(@RequestParam("Case", required = false) data: Long?): Case {
        return data?.let { desktopPartsService.findCase("id",it.toString()) } ?: Case()
    }

    @GetMapping("/searchCPU")
    fun editCPUData(@RequestParam("CPU", required = false) data: Long?): CPU {
        return data?.let{desktopPartsService.findCpu("id",it.toString())}?: CPU()
    }

    @GetMapping("/searchCPUCooler")
    fun editCPUCoolerData(@RequestParam("CPUCooler", required = false) data: Long?): CPUCooler {
        return data?.let{desktopPartsService.findCpuCooler("id",it.toString())}?: CPUCooler()
    }

    @GetMapping("/searchDataStorage")
    fun editDataStorageData(@RequestParam("DataStorage", required = false) data: Long?): DataStorage {
        return data?.let{desktopPartsService.findDataStorage("id",it.toString())}?: DataStorage()
    }

    @GetMapping("/searchGraphicsCard")
    fun editGraphicsCardData(@RequestParam("GraphicsCard", required = false) data: Long?): GraphicsCard {
        return data?.let{desktopPartsService.findGraphicsCard("id",it.toString())}?: GraphicsCard()
    }

    @GetMapping("/searchMemory")
    fun editMemoryData(@RequestParam("Memory", required = false) data: Long?): Memory {
        return data?.let{desktopPartsService.findMemory("id",it.toString())}?: Memory()
    }

    @GetMapping("/searchMotherBoard")
    fun editMotherBoardData(@RequestParam("MotherBoard", required = false) data: Long?): MotherBoard {
        return data?.let{desktopPartsService.findMotherBoard("id",it.toString())}?: MotherBoard()
    }

    @GetMapping("/searchPowerSupply")
    fun editPowerSupplyData(@RequestParam("PowerSupply", required = false) data: Long?): PowerSupply {
        return data?.let{desktopPartsService.findPowerSupply("id",it.toString())}?: PowerSupply()
    }

    @PostMapping("/createEstimate")
    fun postDataTest(@RequestParam("estimate") estimateJSON:String): ResponseEntity<String> {
        val estimateResult=Gson().fromJson(estimateJSON, Estimate::class.java)
        estimateService.saveUserEstimate(estimateResult)
        return ResponseEntity.ok("Success")
    }

    @PostMapping("/uploadPost")
    fun uploadPost(@RequestParam("postData") postJSON:String): ResponseEntity<String> {
        sharePlaceService.uploadPost(Gson().fromJson(postJSON, SharePlacePost::class.java))
        return ResponseEntity.ok("Success")
    }

    @GetMapping("/loadPost")
    fun loadPost(@RequestParam("id") postID:String):HashMap<String,Any>{
        val map=HashMap<String,Any>()
        val post: SharePlacePost = sharePlaceService.loadPost("_id",postID)!!
        map["post"]=post
        map["postEstimate"]=estimateService.getUserEstimateById(ObjectId(post.estimateID))
        map["commentList"]=sharePlaceService.getCommentList("postID",postID)
        return map
    }

    @GetMapping("/loadPostList")
    fun testSharePlacePage(@RequestParam(required = false) searchJson: String?): List<SharePlacePost> {
        var postList=searchJson?.let{
            val temp= Gson().fromJson(searchJson,JsonObject::class.java)
            sharePlaceService.searchPost(temp.get("option").asString,temp.get("value").asString)
        }?:sharePlaceService.getPostList()
        return postList
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