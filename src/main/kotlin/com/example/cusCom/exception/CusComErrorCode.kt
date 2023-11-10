package com.example.cusCom.exception

enum class CusComErrorCode(private val status:Int,
                           private val code:String,
                           private val message:String){

    UnfinishedParts(400,"D000","미완성된 부품 데이터"),
    NotImageData(400,"D001","이미지 데이터가 아님"),

    UnfinishedCusCom(400,"E000","미완성된 견적사항"),
    OversizeMotherBoard(422,"E010","마더보드 허용 규격 초과"),
    OversizeCooler(422,"E020","쿨러 허용 규격 초과"),
    OversizeGraphicsCard(422,"E030","그래픽카드 허용 규격 초과"),
    NotEnoughDataStorage(422,"E040","저장장치 설치 가능 소켓 부족"),
    MismatchMemory(422,"E050","램 타입 불일치"),
    InterferenceMemory(422,"E051","램 간섭 발생"),
    OversizePowerSupply(422,"E060","파워서플라이 허용 규격 초과"),
    PowerSupplyShortage(422,"E061","파워서플라이 전력 부족"),
    FailedDeleteEstimate(404,"E101","견적 삭제 실패"),

    UnfinishedPost(400,"P000","미완성된 게시물"),
    FailedDeletePost(404,"P002","게시물 삭제 실패"),

    NotWrittenComment(400,"C000","빈 댓글 작성 시도"),
    FailedDeleteComment(404,"C001","댓글 삭제 실패");

    fun getStatus():Int { return status }
    fun getCode():String { return code }
    fun getMessage():String { return message }
}