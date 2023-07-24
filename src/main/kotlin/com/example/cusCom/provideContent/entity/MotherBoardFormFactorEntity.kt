package com.example.cusCom.provideContent.entity

import jakarta.persistence.*

@Table(name="motherboard_formfactor")
@Entity
class MotherBoardFormFactorEntity(form_factor:String,
                                  length:Int,
                                  width:Int) {
    @Id
    @Column(nullable = false)
    val form_factor:String=form_factor
    @Column(nullable = false)
    val length:Int=length
    @Column(nullable = false)
    val width:Int=width
}