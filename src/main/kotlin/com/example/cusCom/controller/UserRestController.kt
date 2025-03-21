package com.example.cusCom.controller

import com.example.cusCom.config.DBStringConfig
import com.example.cusCom.config.InnerStringsConfig
import com.example.cusCom.exception.CusComException
import com.example.cusCom.dto.*
import com.example.cusCom.dto.parts.*
import com.example.cusCom.service.DesktopPartsService
import com.example.cusCom.service.SharePlaceService
import com.example.cusCom.service.UserService
import org.bson.types.ObjectId
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/CusCom/API")
class UserRestController(private val desktopPartsService: DesktopPartsService,
                         private val estimateService: com.example.cusCom.service.EstimateService,
                         private val sharePlaceService: SharePlaceService,
                         private val userService: UserService,
                         private val innerStringsConfig: InnerStringsConfig,
                         private val dbStringConfig: DBStringConfig) {

    @PostMapping("/join")
    fun postUserJoin(@RequestBody user: SignInDTO): ResponseEntity<String> {
        userService.joinUser(user)
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }

    @GetMapping("/getUserEstimateList")
    fun userEstimateList(): List<EstimateDTO> {
        val userName=SecurityContextHolder.getContext().authentication.name
        return estimateService.getUserEstimateList(innerStringsConfig.property.userName,userName)
    }

    @GetMapping("/open/getUserEstimate")
    fun userEstimate(@RequestParam("estimateID",required = false) estimateID: String): EstimateDTO {
        return estimateService.getUserEstimateById(estimateID)
    }

    @GetMapping("/open/caseList")
    @ResponseBody
    fun caseListApi(): List<CaseDTO> {
        return desktopPartsService.getCaseList()
    }

    @GetMapping("/open/cpuCoolerList")
    @ResponseBody
    fun cpuCoolerListApi(): List<CpuCoolerDTO> {
        return desktopPartsService.getCpuCoolerList()
    }

    @GetMapping("/open/cpuList")
    @ResponseBody
    fun cpuListApi(): List<CpuDTO> {
        return desktopPartsService.getCPUList()
    }

    @GetMapping("/open/dataStorageList")
    @ResponseBody
    fun dataStorageListApi(): List<DataStorageDTO> {
        return desktopPartsService.getDataStorageList()
    }

    @GetMapping("/open/graphicsCardList")
    @ResponseBody
    fun graphicsCardListApi(): List<GraphicsCardDTO> {
        return desktopPartsService.getGraphicsCardList()
    }

    @GetMapping("/open/memoryList")
    @ResponseBody
    fun memoryListApi(): List<MemoryDTO> {
        return desktopPartsService.getMemoryList()
    }

    @GetMapping("/open/motherBoardList")
    @ResponseBody
    fun motherBoardListApi(): List<MotherBoardDTO> {
        return desktopPartsService.getMotherBoardList()
    }

    @GetMapping("/open/powerSupplyList")
    @ResponseBody
    fun powerSupplyListApi(): List<PowerSupplyDTO> {
        return desktopPartsService.getPowerSupplyList()
    }

    @GetMapping("/open/searchCase")
    fun searchCaseData(@RequestParam("Case", required = false) data: String): CaseDTO {
        return desktopPartsService.findCase(data)
    }

    @GetMapping("/open/searchCPU")
    fun searchCPUData(@RequestParam("CPU", required = false) data: String): CpuDTO {
        return desktopPartsService.findCpu(data)
    }

    @GetMapping("/open/searchCPUCooler")
    fun searchCPUCoolerData(@RequestParam("CPUCooler", required = false) data: String): CpuCoolerDTO {
        return desktopPartsService.findCpuCooler(data)
    }

    @GetMapping("/open/searchDataStorage")
    fun searchDataStorageData(@RequestParam("DataStorage", required = false) data: String): DataStorageDTO {
        return desktopPartsService.findDataStorage(data)
    }

    @GetMapping("/open/searchGraphicsCard")
    fun searchGraphicsCardData(@RequestParam("GraphicsCard", required = false) data: String): GraphicsCardDTO {
        return desktopPartsService.findGraphicsCard(data)
    }

    @GetMapping("/open/searchMemory")
    fun searchMemoryData(@RequestParam("Memory", required = false) data: String): MemoryDTO {
        return desktopPartsService.findMemory(data)
    }

    @GetMapping("/open/searchMotherBoard")
    fun searchMotherBoardData(@RequestParam("MotherBoard", required = false) data: String): MotherBoardDTO {
        return desktopPartsService.findMotherBoard(data)
    }

    @GetMapping("/open/searchPowerSupply")
    fun searchPowerSupplyData(@RequestParam("PowerSupply", required = false) data: String): PowerSupplyDTO {
        return desktopPartsService.findPowerSupply(data)
    }

    @GetMapping("/open/analyzeEstimate")
    fun analyzeEstimate(@RequestParam("estimateID",required = false) estimateID: String?): HashMap<String, Int> {
        val estimate=estimateID?.let { estimateService.getUserEstimateById(it) }
        return estimateService.analyzeEstimate(estimate)
    }

    @PostMapping("/createEstimate")
    fun createEstimate(@RequestParam("estimate") estimateDTO:EstimateDTO): ResponseEntity<String> {
        try{
            estimateService.checkDesktopEstimate(estimateDTO)
            estimateService.saveUserEstimate(estimateDTO)
            return ResponseEntity.ok(innerStringsConfig.property.responseOk)
        }
        catch (e: CusComException) { throw e }
    }

    @PostMapping("/updateEstimate")
    fun updateEstimate(@RequestParam("estimate") estimateDTO:EstimateDTO): ResponseEntity<String> {
        try{
            estimateService.checkDesktopEstimate(estimateDTO)
            estimateService.updateUserEstimate(ObjectId(estimateDTO._id),estimateDTO)
            return ResponseEntity.ok(innerStringsConfig.property.responseOk)
        }
        catch (e: CusComException) { throw e }
    }

    @PostMapping("/deleteEstimate")
    fun deleteEstimate(@RequestParam("estimateID") estimateID:String): ResponseEntity<String>{
        try{
            sharePlaceService.loadPost(innerStringsConfig.request.estimate.id,estimateID).let{ postInfo ->
                if(postInfo.commentCount!=0L)
                    sharePlaceService.deleteComment(innerStringsConfig.request.post.id,postInfo._id)
                sharePlaceService.deletePost(dbStringConfig.mongodb.id,postInfo._id)
            }
            estimateService.deleteUserEstimate(dbStringConfig.mongodb.id,estimateID)
            return ResponseEntity.ok(innerStringsConfig.property.responseOk)
        }catch(e: CusComException) { throw e }
    }

    @PostMapping("/uploadPost")
    fun uploadPost(@RequestParam("postData") sharePlacePostDTO:SharePlacePostDTO,
                   @RequestParam("updated") estimateDTO:EstimateDTO): ResponseEntity<String> {
        estimateService.updateUserEstimate(ObjectId(estimateDTO._id),estimateDTO)
        sharePlaceService.uploadPost(sharePlacePostDTO)
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }

    @GetMapping("/open/loadPost")
    fun loadPost(@RequestParam("id") postID:String):HashMap<String,Any>{
        val map=HashMap<String,Any>()
        val post: SharePlacePostDTO = sharePlaceService.loadPost(dbStringConfig.mongodb.id,postID)
        map[innerStringsConfig.postListMapper.post]=post
        map[innerStringsConfig.postListMapper.postEstimate]=estimateService.getUserEstimateById(post.estimateID)
        map[innerStringsConfig.postListMapper.commentList]=sharePlaceService.getCommentList(innerStringsConfig.request.post.id,postID)
        return map
    }

    @GetMapping("/open/searchPost")
    fun searchPost(@RequestParam("searchJSON",required = false) searchPostDTO: SearchPostDTO): HashMap<String, Any> {
        return sharePlaceService.searchPost(searchPostDTO)
    }

    @PostMapping("/uploadComment")
    fun uploadComment(@RequestParam("commentData") commentDTO:CommentDTO): ResponseEntity<String> {
        sharePlaceService.uploadComment(commentDTO)
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
}