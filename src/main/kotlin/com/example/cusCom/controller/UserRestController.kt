package com.example.cusCom.controller

import com.example.cusCom.config.InnerStringsConfig
import com.example.cusCom.exception.CusComException
import com.example.cusCom.dto.*
import com.example.cusCom.dto.parts.*
import com.example.cusCom.dto.request.*
import com.example.cusCom.dto.response.*
import com.example.cusCom.service.CustomUserDetailsService
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
                         private val customUserDetailsService: CustomUserDetailsService,
                         private val innerStringsConfig: InnerStringsConfig) {

    @PostMapping("/join")
    fun userJoin(@RequestBody user: SignInDTO): ResponseEntity<String> {
        userService.joinUser(user)
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }

    @PostMapping("/signOut")
    fun userSignOut(@RequestBody logOutDTO: LogOutDTO): ResponseEntity<String> {
        userService.signOutUser(logOutDTO)
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }

    @PostMapping("/logIn")
    fun userLogIn(@RequestBody logInDTO: LogInDTO): JwtDTO {
        return userService.logIn(logInDTO)
    }

    @PostMapping("/LogOut")
    fun userLogOut(@RequestBody logOutDTO: LogOutDTO): ResponseEntity<String> {
        userService.logOut(logOutDTO)
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }

    @GetMapping("/getUserEstimateList")
    fun userEstimateList(): List<EstimateDTO> {
        val userName=SecurityContextHolder.getContext().authentication.name
        return estimateService.getUserEstimateList(userName)
    }

    @GetMapping("/open/getUserEstimate")
    fun userEstimate(@RequestParam("estimateID",required = false) estimateID: String): EstimateDTO {
        return estimateService.getUserEstimateById(estimateID)
    }

    @GetMapping("/open/caseList")
    fun caseListApi(@RequestBody partsListPageDTO: PartsListPageDTO): List<CaseDTO> {
        return desktopPartsService.getCaseList(partsListPageDTO)
    }

    @GetMapping("/open/cpuCoolerList")
    fun cpuCoolerListApi(@RequestBody partsListPageDTO: PartsListPageDTO): List<CpuCoolerDTO> {
        return desktopPartsService.getCpuCoolerList(partsListPageDTO)
    }

    @GetMapping("/open/cpuList")
    fun cpuListApi(@RequestBody partsListPageDTO: PartsListPageDTO): List<CpuDTO> {
        return desktopPartsService.getCPUList(partsListPageDTO)
    }

    @GetMapping("/open/dataStorageList")
    fun dataStorageListApi(@RequestBody partsListPageDTO: PartsListPageDTO): List<DataStorageDTO> {
        return desktopPartsService.getDataStorageList(partsListPageDTO)
    }

    @GetMapping("/open/graphicsCardList")
    fun graphicsCardListApi(@RequestBody partsListPageDTO: PartsListPageDTO): List<GraphicsCardDTO> {
        return desktopPartsService.getGraphicsCardList(partsListPageDTO)
    }

    @GetMapping("/open/memoryList")
    fun memoryListApi(@RequestBody partsListPageDTO: PartsListPageDTO): List<MemoryDTO> {
        return desktopPartsService.getMemoryList(partsListPageDTO)
    }

    @GetMapping("/open/motherBoardList")
    fun motherBoardListApi(@RequestBody partsListPageDTO: PartsListPageDTO): List<MotherBoardDTO> {
        return desktopPartsService.getMotherBoardList(partsListPageDTO)
    }

    @GetMapping("/open/powerSupplyList")
    fun powerSupplyListApi(@RequestBody partsListPageDTO: PartsListPageDTO): List<PowerSupplyDTO> {
        return desktopPartsService.getPowerSupplyList(partsListPageDTO)
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

    @PostMapping("/createEstimate")
    fun createEstimate(@RequestBody estimateAnalyzeDTO: EstimateAnalyzeDTO): ResponseEntity<String> {
        val requestUser = customUserDetailsService.loadUserByAuthentication()
        try{
            estimateService.checkDesktopEstimate(estimateAnalyzeDTO)
            estimateService.saveUserEstimate(estimateAnalyzeDTO,requestUser.username)
            return ResponseEntity.ok(innerStringsConfig.property.responseOk)
        }
        catch (e: CusComException) { throw e }
    }

    @PostMapping("/updateEstimate")
    fun updateEstimate(@RequestBody estimateAnalyzeDTO: EstimateAnalyzeDTO): ResponseEntity<String> {
        val requestUser = customUserDetailsService.loadUserByAuthentication()
        try{
            estimateService.checkDesktopEstimate(estimateAnalyzeDTO)
            estimateService.updateUserEstimate(
                ObjectId(estimateAnalyzeDTO.estimateId),
                estimateAnalyzeDTO,
                requestUser.username)
            return ResponseEntity.ok(innerStringsConfig.property.responseOk)
        }
        catch (e: CusComException) { throw e }
    }

    @PostMapping("/deleteEstimate")
    fun deleteEstimate(@RequestBody estimateID:String): ResponseEntity<String>{
        val requestUser = customUserDetailsService.loadUserByAuthentication()
        try{
            sharePlaceService.loadPost(estimateID).let{ postInfo ->
                if(postInfo.commentCount!=0L)
                    sharePlaceService.deleteComment(postInfo._id,requestUser.username)
                sharePlaceService.deletePost(postInfo._id,requestUser.username)
            }
            estimateService.deleteUserEstimate(estimateID,requestUser.username)
            return ResponseEntity.ok(innerStringsConfig.property.responseOk)
        }catch(e: CusComException) { throw e }
    }

    @PostMapping("/uploadPost")
    fun uploadPost(@RequestBody saveSharePlacePostDTO: SaveSharePlacePostDTO): ResponseEntity<String> {
        val requestUser = customUserDetailsService.loadUserByAuthentication()
        sharePlaceService.uploadPost(saveSharePlacePostDTO,requestUser.username)
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }

    @GetMapping("/open/loadPost")
    fun loadPost(@RequestParam("id") postID:String):HashMap<String,Any>{
        val map=HashMap<String,Any>()
        val post: SharePlacePostDTO = sharePlaceService.loadPost(postID)
        map[innerStringsConfig.postListMapper.post]=post
        map[innerStringsConfig.postListMapper.postEstimate]=estimateService.getUserEstimateById(post.estimateID)
        map[innerStringsConfig.postListMapper.commentList]=sharePlaceService.getCommentList(postID)
        return map
    }

    @GetMapping("/open/searchPost")
    fun searchPost(@RequestParam("searchOption",required = false) searchPostDTO: SearchPostDTO): HashMap<String, Any> {
        return sharePlaceService.searchPost(searchPostDTO)
    }

    @PostMapping("/uploadComment")
    fun uploadComment(@RequestBody saveCommentDTO: SaveCommentDTO): ResponseEntity<String> {
        val requestUser = customUserDetailsService.loadUserByAuthentication()
        sharePlaceService.uploadComment(saveCommentDTO,requestUser.username)
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