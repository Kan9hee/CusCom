package com.example.cusCom.enums

enum class MotherBoardFormFactor(val length: Int, val width: Int) {
    ATX(305, 244),
    E_ATX(330, 244),
    M_ATX(244, 244);

    companion object {
        fun fromString(value: String): MotherBoardFormFactor { return valueOf(value) }
    }
}