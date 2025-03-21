package com.example.cusCom.entity.mySQL.parts

import com.example.cusCom.dto.parts.DataStorageDTO
import jakarta.persistence.*

@Entity
@Table(name = "data_storage")
class DataStorageEntity(
    name: String,
    imageUrl: String,
    manufacturer: String,
    storageInterface: String,
    formFactor: String,
    capacity: String,
    readSpeed: Int,
    writeSpeed: Int
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long? = null

    @Column(nullable = false, unique = true)
    var name: String = name
        protected set

    @Column(nullable = false)
    var imageUrl: String = imageUrl
        protected set

    @Column(nullable = false)
    var manufacturer: String = manufacturer
        protected set

    @Column(nullable = false)
    var storageInterface: String = storageInterface
        protected set

    @Column(nullable = false)
    var formFactor: String = formFactor
        protected set

    @Column(nullable = false)
    var capacity: String = capacity
        protected set

    @Column(nullable = false)
    var readSpeed: Int = readSpeed
        protected set

    @Column(nullable = false)
    var writeSpeed: Int = writeSpeed
        protected set

    fun update(dataStorageDTO: DataStorageDTO) {
        this.name = dataStorageDTO.name
        this.imageUrl = dataStorageDTO.imageUrl
        this.manufacturer = dataStorageDTO.manufacturer
        this.storageInterface = dataStorageDTO.storageInterface
        this.formFactor = dataStorageDTO.formFactor
        this.capacity = dataStorageDTO.capacity
        this.readSpeed = dataStorageDTO.readSpeed
        this.writeSpeed = dataStorageDTO.writeSpeed
    }
}
