package com.example.cusCom.controller

import com.example.cusCom.config.DBStringConfig
import com.example.cusCom.config.InnerStringsConfig
import com.example.cusCom.exception.CusComException
import com.example.cusCom.provideContent.dto.*
import com.example.cusCom.provideContent.dto.parts.*
import com.example.cusCom.provideContent.service.DesktopPartsService
import com.example.cusCom.provideContent.service.EstimateService
import com.example.cusCom.provideContent.service.SharePlaceService
import com.example.cusCom.provideContent.service.UserService
import com.fasterxml.jackson.databind.ObjectMapper
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
                         private val userService: UserService,
                         private val innerStringsConfig: InnerStringsConfig,
                         private val dbStringConfig: DBStringConfig) {

    @PostMapping("/join")
    fun postUserJoin(@RequestBody user: User): ResponseEntity<String> {
        userService.joinUser(user)
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }

    @GetMapping("/checkLogin")
    fun checkLogin(): ResponseEntity<String> {
        val authentication = SecurityContextHolder.getContext().authentication
        val loggedIn = authentication != null && authentication.isAuthenticated
        val responseJson = ObjectMapper().writeValueAsString(LoginStatus(loggedIn))
        return ResponseEntity.ok(responseJson)
    }

    @GetMapping("/getUserEstimateList")
    fun userEstimateList(): List<Estimate> {
        val userName=SecurityContextHolder.getContext().authentication.name
        return estimateService.getUserEstimateList(innerStringsConfig.property.userName,userName)
    }

    @GetMapping("/open/getUserEstimate")
    fun userEstimate(@RequestParam("estimateID",required = false) estimateID: String?): Estimate {
        return estimateID?.let { estimateService.getUserEstimateById(it) }?: Estimate()
    }

    @GetMapping("/open/caseList")
    @ResponseBody
    fun caseListApi(): List<Case> {
        return desktopPartsService.getCaseList()
    }

    @GetMapping("/open/cpuCoolerList")
    @ResponseBody
    fun cpuCoolerListApi(): List<CPUCooler> {
        return desktopPartsService.getCpuCoolerList()
    }

    @GetMapping("/open/cpuList")
    @ResponseBody
    fun cpuListApi(): List<CPU> {
        return desktopPartsService.getCPUList()
    }

    @GetMapping("/open/dataStorageList")
    @ResponseBody
    fun dataStorageListApi(): List<DataStorage> {
        return desktopPartsService.getDataStorageList()
    }

    @GetMapping("/open/graphicsCardList")
    @ResponseBody
    fun graphicsCardListApi(): List<GraphicsCard> {
        return desktopPartsService.getGraphicsCardList()
    }

    @GetMapping("/open/memoryList")
    @ResponseBody
    fun memoryListApi(): List<Memory> {
        return desktopPartsService.getMemoryList()
    }

    @GetMapping("/open/motherBoardList")
    @ResponseBody
    fun motherBoardListApi(): List<MotherBoard> {
        return desktopPartsService.getMotherBoardList()
    }

    @GetMapping("/open/powerSupplyList")
    @ResponseBody
    fun powerSupplyListApi(): List<PowerSupply> {
        return desktopPartsService.getPowerSupplyList()
    }

    @GetMapping("/open/searchCase")
    @ResponseBody
    fun searchCaseApi(@RequestParam("Case", required = false) data: Long?): Case {
        return data?.let { desktopPartsService.findCase(dbStringConfig.mysql.id,it.toString()) }
            ?: Case()
    }

    @GetMapping("/open/searchCPU")
    fun editCPUData(@RequestParam("CPU", required = false) data: Long?): CPU {
        return data?.let{desktopPartsService.findCpu(dbStringConfig.mysql.id,it.toString())}
            ?: CPU()
    }

    @GetMapping("/open/searchCPUCooler")
    fun editCPUCoolerData(@RequestParam("CPUCooler", required = false) data: Long?): CPUCooler {
        return data?.let{desktopPartsService.findCpuCooler(dbStringConfig.mysql.id,it.toString())}
            ?: CPUCooler()
    }

    @GetMapping("/open/searchDataStorage")
    fun editDataStorageData(@RequestParam("DataStorage", required = false) data: Long?): DataStorage {
        return data?.let{desktopPartsService.findDataStorage(dbStringConfig.mysql.id,it.toString())}
            ?: DataStorage()
    }

    @GetMapping("/open/searchGraphicsCard")
    fun editGraphicsCardData(@RequestParam("GraphicsCard", required = false) data: Long?): GraphicsCard {
        return data?.let{desktopPartsService.findGraphicsCard(dbStringConfig.mysql.id,it.toString())}
            ?: GraphicsCard()
    }

    @GetMapping("/open/searchMemory")
    fun editMemoryData(@RequestParam("Memory", required = false) data: Long?): Memory {
        return data?.let{desktopPartsService.findMemory(dbStringConfig.mysql.id,it.toString())}
            ?: Memory()
    }

    @GetMapping("/open/searchMotherBoard")
    fun editMotherBoardData(@RequestParam("MotherBoard", required = false) data: Long?): MotherBoard {
        return data?.let{desktopPartsService.findMotherBoard(dbStringConfig.mysql.id,it.toString())}
            ?: MotherBoard()
    }

    @GetMapping("/open/searchPowerSupply")
    fun editPowerSupplyData(@RequestParam("PowerSupply", required = false) data: Long?): PowerSupply {
        return data?.let{desktopPartsService.findPowerSupply(dbStringConfig.mysql.id,it.toString())}
            ?: PowerSupply()
    }

    @GetMapping("/open/analyzeEstimate")
    fun analyzeEstimate(@RequestParam("estimateID",required = false) estimateID: String?): HashMap<String, Int> {
        val estimate=estimateID?.let { estimateService.getUserEstimateById(it) }
        return estimateService.analyzeEstimate(estimate)
    }

    @PostMapping("/createEstimate")
    fun createEstimate(@RequestParam("estimate") estimateJSON:String): ResponseEntity<String> {
        try{
            val estimateResult=Gson().fromJson(inputUserData(estimateJSON), Estimate::class.java)
            estimateService.checkDesktopEstimate(estimateResult)
            estimateService.saveUserEstimate(estimateResult)
            return ResponseEntity.ok(innerStringsConfig.property.responseOk)
        }
        catch (e: CusComException) { throw e }
    }

    @PostMapping("/updateEstimate")
    fun updateEstimate(@RequestParam("estimate") estimateJSON:String): ResponseEntity<String> {
        try{
            val estimateResult=Gson().fromJson(inputUserData(estimateJSON), Estimate::class.java)
            estimateService.checkDesktopEstimate(estimateResult)
            estimateService.updateUserEstimate(ObjectId(estimateResult._id),estimateResult)
            return ResponseEntity.ok(innerStringsConfig.property.responseOk)
        }
        catch (e: CusComException) { throw e }
    }

    @PostMapping("/deleteEstimate")
    fun deleteEstimate(@RequestParam("estimateID") estimateID:String): ResponseEntity<String>{
        try{
            sharePlaceService.loadPost(innerStringsConfig.request.estimate.id,estimateID)?.let{ postInfo ->
                if(postInfo.commentCount!=0L)
                    sharePlaceService.deleteComment(innerStringsConfig.request.post.id,postInfo._id)
                sharePlaceService.deletePost(dbStringConfig.mongodb.id,postInfo._id)
            }
            estimateService.deleteUserEstimate(dbStringConfig.mongodb.id,estimateID)
            return ResponseEntity.ok(innerStringsConfig.property.responseOk)
        }catch(e: CusComException) { throw e }
    }

    @PostMapping("/uploadPost")
    fun uploadPost(@RequestParam("postData") postJSON:String,
                   @RequestParam("updated") estimateJSON:String): ResponseEntity<String> {
        val updatedEstimate=Gson().fromJson(estimateJSON,Estimate::class.java)
        estimateService.updateUserEstimate(ObjectId(updatedEstimate._id),updatedEstimate)
        sharePlaceService.uploadPost(Gson().fromJson(inputUserData(postJSON), SharePlacePost::class.java))
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }

    @GetMapping("/open/loadPost")
    fun loadPost(@RequestParam("id") postID:String):HashMap<String,Any>{
        val map=HashMap<String,Any>()
        val post: SharePlacePost = sharePlaceService.loadPost(dbStringConfig.mongodb.id,postID)!!
        map[innerStringsConfig.postListMapper.post]=post
        map[innerStringsConfig.postListMapper.postEstimate]=estimateService.getUserEstimateById(post.estimateID)
        map[innerStringsConfig.postListMapper.commentList]=sharePlaceService.getCommentList(innerStringsConfig.request.post.id,postID)
        return map
    }

    @GetMapping("/open/loadPostList")
    fun loadPostList(@RequestParam("searchJSON",required = false) searchJson: String?,
                     @RequestParam("maxContent") maxContent: Int,
                     @RequestParam("currentPage") currentPage: Int): HashMap<String, Any> {
        var postList=searchJson?.let{
            val temp=Gson().fromJson(searchJson,JsonObject::class.java)
            sharePlaceService.searchPost(temp.get(innerStringsConfig.property.searchOption).asString,
                temp.get(innerStringsConfig.property.searchKeyword).asString,maxContent,currentPage)
        }?:sharePlaceService.getPostList(maxContent,currentPage)
        return postList
    }

    @PostMapping("/uploadComment")
    fun uploadComment(@RequestParam("commentData") commentJSON:String): ResponseEntity<String> {
        sharePlaceService.uploadComment(Gson().fromJson(inputUserData(commentJSON), Comment::class.java))
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }

    @PostMapping("/increaseLike")
    fun increaseLike(@RequestParam("likeAction") action:String): ResponseEntity<String> {
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }

    @PostMapping("/decreaseLike")
    fun decreaseLike(@RequestParam("dislikeAction") action:String): ResponseEntity<String> {
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }

    private fun inputUserData(jsonString:String):String{
        val jsonObject=Gson().fromJson(jsonString, JsonObject::class.java)
        jsonObject.addProperty(innerStringsConfig.property.userName, SecurityContextHolder.getContext().authentication.name)
        return Gson().toJson(jsonObject)
    }
}