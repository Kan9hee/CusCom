package com.example.cusCom.provideContent.entity.parts

import com.example.cusCom.provideContent.dto.parts.DataStorage
import jakarta.persistence.*

@Table(name="data_storage")
@Entity
class DataStorageEntity(dataStorage: DataStorage){

    @Id
    @Column(nullable = false)
    var name:String=dataStorage.name
        protected set

    @Column(nullable=false)
    var imageUrl:String=dataStorage.imageUrl
        protected set

    @Column(nullable = false)
    var manufacturer:String=dataStorage.manufacturer
        protected set

    @Column(nullable = false)
    var storageInterface:String=dataStorage.storageInterface
        protected set

    @Column(nullable = false)
    var formFactor:String=dataStorage.formFactor
        protected set

    @Column(nullable = false)
    var capacity:String=dataStorage.capacity
        protected set

    @Column(nullable = false)
    var readSpeed:Int=dataStorage.readSpeed
        protected set

    @Column(nullable = false)
    var writeSpeed:Int=dataStorage.writeSpeed
        protected set

    fun update(dataStorage: DataStorage){
        this.name=dataStorage.name
        this.imageUrl=dataStorage.imageUrl
        this.manufacturer=dataStorage.manufacturer
        this.storageInterface=dataStorage.storageInterface
        this.formFactor=dataStorage.formFactor
        this.capacity=dataStorage.capacity
        this.readSpeed=dataStorage.readSpeed
        this.writeSpeed=dataStorage.writeSpeed
    }
}
