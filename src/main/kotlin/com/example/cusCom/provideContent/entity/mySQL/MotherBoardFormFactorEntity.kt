package com.example.cusCom.provideContent.entity.mySQL

import jakarta.persistence.*

@Table(name="motherboard_formfactor")
@Entity
class MotherBoardFormFactorEntity(name:String,
                                  length:Int,
                                  width:Int) {
    @Id
    @Column(nullable = false)
    val name:String=name
    @Column(nullable = false)
    val length:Int=length
    @Column(nullable = false)
    val width:Int=width
}