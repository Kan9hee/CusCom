package com.example.cusCom.controller

import com.example.cusCom.component.JwtComponent
import com.example.cusCom.config.InnerStringsConfig
import com.example.cusCom.exception.CusComException
import com.example.cusCom.dto.*
import com.example.cusCom.dto.parts.*
import com.example.cusCom.dto.request.*
import com.example.cusCom.dto.response.*
import com.example.cusCom.enums.AccountRole
import com.example.cusCom.exception.CusComErrorCode
import com.example.cusCom.service.*
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
                         private val tokenService: TokenService,
                         private val customUserDetailsService: CustomUserDetailsService,
                         private val innerStringsConfig: InnerStringsConfig,
                         private val jwtComponent: JwtComponent) {

    @GetMapping("/isAdmin")
    fun isAdmin(): Boolean {
        val authentication = SecurityContextHolder.getContext().authentication
        return authentication
            ?.authorities
            ?.any { it.authority == "ROLE_${AccountRole.ADMIN}" }
            ?: false
    }

    @PostMapping("/open/join")
    fun userJoin(@RequestBody user: SignInDTO): ResponseEntity<String> {
        userService.joinUser(user)
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }

    @PostMapping("/signOut")
    fun userSignOut(@RequestBody logOutDTO: LogOutDTO): ResponseEntity<String> {
        userService.signOutUser(logOutDTO)
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }

    @PostMapping("/open/logIn")
    fun userLogIn(@RequestBody logInDTO: LogInDTO): JwtDTO {
        val authentication = userService.logIn(logInDTO)
        if(authentication!=null){
            val generatedJwt = jwtComponent.generateToken(authentication)
            tokenService.saveRefreshToken(generatedJwt.refreshToken, logInDTO.insertedID)
            return generatedJwt
        }
        else
            throw CusComException(CusComErrorCode.UnauthorizedToken)
    }

    @PostMapping("/logOut")
    fun userLogOut(@RequestBody logOutDTO: LogOutDTO): ResponseEntity<String> {
        logOutDTO.accessToken?.takeIf { jwtComponent.validateToken(it) }
            ?.let { tokenService.saveBlacklistToken(logOutDTO.accessToken) }
        userService.logOut(logOutDTO)
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }

    @GetMapping("/checkAccessToken")
    fun checkAccessToken(): ResponseEntity<String> {
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }

    @PostMapping("/open/reissueAccessToken")
    fun reissueAccessToken(@RequestBody jwtDTO: JwtDTO): JwtDTO{
        try{
            val accountIdString = tokenService.getAccountIdFromRefreshToken(jwtDTO.refreshToken)
            ?: throw CusComException(CusComErrorCode.MisinformationToken)
            val user = customUserDetailsService.loadUserByUsername(accountIdString)
            return jwtComponent.reissueAccessToken(jwtDTO.accessToken,jwtDTO.refreshToken,user)
        }
        catch (e: CusComException) { throw e }
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
    fun caseListApi(@RequestParam maxContent: Int,
                    @RequestParam page: Int): List<CaseDTO> {
        return desktopPartsService.getCaseList(maxContent,page)
    }

    @GetMapping("/open/cpuCoolerList")
    fun cpuCoolerListApi(@RequestParam maxContent: Int,
                         @RequestParam page: Int): List<CpuCoolerDTO> {
        return desktopPartsService.getCpuCoolerList(maxContent,page)
    }

    @GetMapping("/open/cpuList")
    fun cpuListApi(@RequestParam maxContent: Int,
                   @RequestParam page: Int): List<CpuDTO> {
        return desktopPartsService.getCPUList(maxContent,page)
    }

    @GetMapping("/open/dataStorageList")
    fun dataStorageListApi(@RequestParam maxContent: Int,
                           @RequestParam page: Int): List<DataStorageDTO> {
        return desktopPartsService.getDataStorageList(maxContent,page)
    }

    @GetMapping("/open/graphicsCardList")
    fun graphicsCardListApi(@RequestParam maxContent: Int,
                            @RequestParam page: Int): List<GraphicsCardDTO> {
        return desktopPartsService.getGraphicsCardList(maxContent,page)
    }

    @GetMapping("/open/memoryList")
    fun memoryListApi(@RequestParam maxContent: Int,
                      @RequestParam page: Int): List<MemoryDTO> {
        return desktopPartsService.getMemoryList(maxContent,page)
    }

    @GetMapping("/open/motherBoardList")
    fun motherBoardListApi(@RequestParam maxContent: Int,
                           @RequestParam page: Int): List<MotherBoardDTO> {
        return desktopPartsService.getMotherBoardList(maxContent,page)
    }

    @GetMapping("/open/powerSupplyList")
    fun powerSupplyListApi(@RequestParam maxContent: Int,
                           @RequestParam page: Int): List<PowerSupplyDTO> {
        return desktopPartsService.getPowerSupplyList(maxContent,page)
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
        }
        catch (e: CusComException) { throw e }
    }

    @PostMapping("/uploadPost")
    fun uploadPost(@RequestBody saveSharePlacePostDTO: SaveSharePlacePostDTO): ResponseEntity<String> {
        try{
            val requestUser = customUserDetailsService.loadUserByAuthentication()
            sharePlaceService.uploadPost(saveSharePlacePostDTO,requestUser.username)
            return ResponseEntity.ok(innerStringsConfig.property.responseOk)
        }
        catch (e: CusComException) { throw e }
    }

    @GetMapping("/open/loadPost")
    fun loadPost(@RequestParam("id") postID:String):HashMap<String,Any>{
        try{
            val map=HashMap<String,Any>()
            val post = sharePlaceService.loadPost(postID)
            map[innerStringsConfig.postListMapper.post]=post
            map[innerStringsConfig.postListMapper.postEstimate]=estimateService.getUserEstimateById(post.estimateID)
            map[innerStringsConfig.postListMapper.commentList]=sharePlaceService.getCommentList(postID)
            return map
        }
        catch (e: CusComException) { throw e }
    }

    @GetMapping("/open/searchPost")
    fun searchPost(@RequestParam("option") option: String,
                   @RequestParam("keyword") keyword: String?,
                   @RequestParam("maxContent") maxContent: Int,
                   @RequestParam("page") page: Int): HashMap<String, Any> {
        val searchPostDTO = SearchPostDTO(option, keyword, maxContent, page)
        return sharePlaceService.searchPost(searchPostDTO)
    }

    @PostMapping("/uploadComment")
    fun uploadComment(@RequestBody saveCommentDTO: SaveCommentDTO): ResponseEntity<String> {
        val requestUser = customUserDetailsService.loadUserByAuthentication()
        sharePlaceService.uploadComment(saveCommentDTO,requestUser.username)
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }

    @PostMapping("/increaseLike")
    fun increaseLike(@RequestParam("postId") postId:String): ResponseEntity<String> {
        sharePlaceService.increasePostLikeCount(postId)
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }

    @PostMapping("/decreaseLike")
    fun decreaseLike(@RequestParam("postId") postId:String): ResponseEntity<String> {
        sharePlaceService.decreasePostLikeCount(postId)
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }
}