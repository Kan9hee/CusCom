package com.example.cusCom.controller

import com.example.cusCom.exception.EstimateErrorCode
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
import org.springframework.ui.Model
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
        return ResponseEntity.ok("{\"loggedIn\": ${authentication != null && authentication.isAuthenticated()}}");
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
        return desktopPartsService.getCaseList();
    }

    @GetMapping("/cpuCoolerList")
    @ResponseBody
    fun cpuCoolerListApi(): List<CPUCooler> {
        return desktopPartsService.getCpuCoolerList();
    }

    @GetMapping("/cpuList")
    @ResponseBody
    fun cpuListApi(): List<CPU> {
        return desktopPartsService.getCPUList();
    }

    @GetMapping("/dataStorageList")
    @ResponseBody
    fun dataStorageListApi(): List<DataStorage> {
        return desktopPartsService.getDataStorageList();
    }

    @GetMapping("/graphicsCardList")
    @ResponseBody
    fun graphicsCardListApi(): List<GraphicsCard> {
        return desktopPartsService.getGraphicsCardList();
    }

    @GetMapping("/memoryList")
    @ResponseBody
    fun memoryListApi(): List<Memory> {
        return desktopPartsService.getMemoryList();
    }

    @GetMapping("/motherBoardList")
    @ResponseBody
    fun motherBoardListApi(): List<MotherBoard> {
        return desktopPartsService.getMotherBoardList();
    }

    @GetMapping("/powerSupplyList")
    @ResponseBody
    fun powerSupplyListApi(): List<PowerSupply> {
        return desktopPartsService.getPowerSupplyList();
    }

    @GetMapping("/searchCase")
    @ResponseBody
    fun searchCaseApi(@RequestParam("Case", required = false) data: String?): Case {
        return data?.let { desktopPartsService.findCase(it) } ?: Case()
    }

    @GetMapping("/searchCPU")
    fun editCPUData(@RequestParam("CPU", required = false) data: String?): CPU {
        return data?.let{desktopPartsService.findCpu(it)}?: CPU()
    }

    @GetMapping("/searchCPUCooler")
    fun editCPUCoolerData(@RequestParam("CPUCooler", required = false) data: String?): CPUCooler {
        return data?.let{desktopPartsService.findCpuCooler(it)}?: CPUCooler()
    }

    @GetMapping("/searchDataStorage")
    fun editDataStorageData(@RequestParam("DataStorage", required = false) data: String?): DataStorage {
        return data?.let{desktopPartsService.findDataStorage(it)}?: DataStorage()
    }

    @GetMapping("/searchGraphicsCard")
    fun editGraphicsCardData(@RequestParam("GraphicsCard", required = false) data: String?): GraphicsCard {
        return data?.let{desktopPartsService.findGraphicsCard(it)}?: GraphicsCard()
    }

    @GetMapping("/searchMemory")
    fun editMemoryData(@RequestParam("Memory", required = false) data: String?): Memory {
        return data?.let{desktopPartsService.findMemory(it)}?: Memory()
    }

    @GetMapping("/searchMotherBoard")
    fun editMotherBoardData(@RequestParam("MotherBoard", required = false) data: String?): MotherBoard {
        return data?.let{desktopPartsService.findMotherBoard(it)}?: MotherBoard()
    }

    @GetMapping("/searchPowerSupply")
    fun editPowerSupplyData(@RequestParam("PowerSupply", required = false) data: String?): PowerSupply {
        return data?.let{desktopPartsService.findPowerSupply(it)}?: PowerSupply()
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
    fun testSharePlacePage(@RequestParam(defaultValue = "1") page: Int,
                           @RequestParam(defaultValue = "9") pageSize: Int,
                           @RequestParam(required = false) searchJson: String?):HashMap<String,Any>{
        var postList=searchJson?.let{
            val temp= Gson().fromJson(searchJson,JsonObject::class.java)
            sharePlaceService.searchPost(temp.get("option").asString,temp.get("value").asString)
        }?:sharePlaceService.getPostList()

        val listSize = postList.size
        val listStartIndex = (page - 1) * pageSize
        val listEndIndex = kotlin.math.min(listStartIndex + pageSize, listSize)
        postList = postList.subList(listStartIndex,listEndIndex)

        val map=HashMap<String,Any>()
        map["postList"]=postList
        map["totalPages"]=kotlin.math.ceil(postList.size.toDouble()/pageSize).toInt()
        map["currentPage"]=page
        return map
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
            if(estimate.run { cpu.isEmpty() ||
                        desktopCase.isEmpty() ||
                        dataStorage.isEmpty() ||
                        memory.isEmpty() ||
                        graphicsCard.isEmpty() ||
                        cpuCooler.isEmpty() ||
                        motherBoard.isEmpty() ||
                        powerSupply.isEmpty() })
                throw EstimateException(EstimateErrorCode.UnfinishedEstimate)
            estimateService.checkDesktopEstimate(estimate)
        }
        check.onFailure { ex -> throw ex }
    }
}