package com.example.cusCom.estimate.model.parts

import jakarta.persistence.*

@Table(name="memory")
@Entity
class MemoryEntity(name:String,
                   manufacturer:String,
                   type:String,
                   capacity:Int,
                   height:Int){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?=null

    @Column(nullable = false, unique = true)
    val name:String=name
    @Column(nullable = false)
    val manufacturer:String=manufacturer
    @Column(nullable = false)
    val type:String=type
    @Column(nullable = false)
    val capacity:Int=capacity
    @Column(nullable = false)
    val height:Int=height
}
