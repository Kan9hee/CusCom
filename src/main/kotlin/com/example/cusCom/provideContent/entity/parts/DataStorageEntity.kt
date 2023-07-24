package com.example.cusCom.provideContent.entity.parts

import com.example.cusCom.provideContent.dto.parts.DataStorage
import jakarta.persistence.*

@Table(name="data_storage")
@Entity
class DataStorageEntity(dataStorage: DataStorage){
    @Id
    @Column(nullable = false)
    var name:String=dataStorage.name
    @Column(nullable = false)
    var manufacturer:String=dataStorage.manufacturer
    @Column(nullable = false)
    var storageInterface:String=dataStorage.storageInterface
    @Column(nullable = false)
    var formFactor:String=dataStorage.formFactor
    @Column(nullable = false)
    var capacity:String=dataStorage.capacity
    @Column(nullable = false)
    var readSpeed:Int=dataStorage.readSpeed
    @Column(nullable = false)
    var writeSpeed:Int=dataStorage.writeSpeed

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
