package com.example.cusCom.exception

enum class CusComErrorCode(private val status:Int,
                           private val code:String,
                           private val message:String){
    NotImageData(400,"D001","올바른 이미지 데이터를 삽입해야 함"),
    FormFactorNotFound(404,"D002","마더보드 폼팩터 데이터 매칭 실패"),
    CaseNotFound(404,"D011","케이스 데이터 매칭 실패"),
    CPUNotFound(404,"D012","CPU 데이터 매칭 실패"),
    CPUCoolerNotFound(404,"D013","CPU 쿨러 데이터 매칭 실패"),
    DataStorageNotFound(404,"D014","저장장치 데이터 매칭 실패"),
    GraphicsCardNotFound(404,"D015","그래픽카드 데이터 매칭 실패"),
    MemoryNotFound(404,"D016","메모리 데이터 매칭 실패"),
    MotherBoardNotFound(404,"D017","마더보드 데이터 매칭 실패"),
    PowerSupplyNotFound(404,"D018","파워서플라이 데이터 매칭 실패"),

    UnfinishedEstimate(400,"E000","미완성된 견적사항"),
    NotFoundEstimate(404,"E001","견적사항 찾을 수 없음"),
    OversizeMotherBoard(422,"E010","마더보드 허용 규격 초과"),
    MismatchSocket(422,"E011","마더보드 소켓 불일치"),
    MismatchChipset(422,"E012","마더보드 칩셋 불일치"),
    OversizeCooler(422,"E020","쿨러 허용 규격 초과"),
    OversizeGraphicsCard(422,"E030","그래픽카드 허용 규격 초과"),
    NotEnoughDataStorage(422,"E040","저장장치 설치 가능 소켓 부족"),
    MismatchMemory(422,"E050","램 타입 불일치"),
    InterferenceMemory(422,"E051","램 간섭 발생"),
    NotEnoughMemory(422,"E052","램 설치 가능 소켓 부족"),
    OversizePowerSupply(422,"E060","파워서플라이 허용 규격 초과"),
    PowerSupplyShortage(422,"E061","파워서플라이 전력 부족"),
    FailedDeleteEstimate(404,"E101","견적 삭제 실패"),

    UserDataNotFound(404,"U001","계정을 찾을 수 없습니다."),
    PasswordNotPatch(400,"U002","비밀번호가 일치하지 않습니다."),
    UnauthorizedToken(400,"U003","권한 없는 토큰입니다."),
    MisinformationToken(404,"U004","토큰에 저장된 계정 정보가 없습니다."),
    DuplicateAccountID(409,"U005","이미 가입된 계정입니다."),
    DuplicateNickName(409,"U006","이미 사용 중인 닉네임입니다."),

    UnfinishedPost(400,"P000","미완성된 게시물"),
    PostNotFound(404,"P001","게시물을 찾을 수 없음"),
    FailedDeletePost(404,"P002","게시물 삭제 실패"),

    NotWrittenComment(400,"C000","빈 댓글 작성 시도"),
    FailedDeleteComment(404,"C001","댓글 삭제 실패");

    fun getStatus():Int { return status }
    fun getCode():String { return code }
    fun getMessage():String { return message }
}