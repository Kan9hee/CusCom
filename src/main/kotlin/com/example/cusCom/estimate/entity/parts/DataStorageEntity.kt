package com.example.cusCom.estimate.model.parts

import jakarta.persistence.*

@Table(name="data_storage")
@Entity
class DataStorageEntity(name:String,
                        manufacturer:String,
                        storageInterface:String,
                        formFactor:String,
                        capacity:String,
                        readSpeed:Int,
                        writeSpeed:Int){
    @Id
    @Column(nullable = false)
    val name:String=name
    @Column(nullable = false)
    val manufacturer:String=manufacturer
    @Column(nullable = false)
    val storageInterface:String=storageInterface
    @Column(nullable = false)
    val formFactor:String=formFactor
    @Column(nullable = false)
    val capacity:String=capacity
    @Column(nullable = false)
    val readSpeed:Int=readSpeed
    @Column(nullable = false)
    val writeSpeed:Int=writeSpeed
}
