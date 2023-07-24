package com.example.cusCom.provideContent.dto.parts

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
    var name:String=name
    @Column(nullable = false)
    var manufacturer:String=manufacturer
    @Column(nullable = false)
    var storageInterface:String=storageInterface
    @Column(nullable = false)
    var formFactor:String=formFactor
    @Column(nullable = false)
    var capacity:String=capacity
    @Column(nullable = false)
    var readSpeed:Int=readSpeed
    @Column(nullable = false)
    var writeSpeed:Int=writeSpeed

    fun update(dataStorage: DataStorage){
        this.name=dataStorage.name
        this.manufacturer=dataStorage.manufacturer
        this.storageInterface=dataStorage.storageInterface
        this.formFactor=dataStorage.formFactor
        this.capacity=dataStorage.capacity
        this.readSpeed=dataStorage.readSpeed
        this.writeSpeed=dataStorage.writeSpeed
    }
}
