package com.example.cusCom.entity.mySQL.parts

import com.example.cusCom.dto.parts.MemoryDTO
import jakarta.persistence.*

@Entity
@Table(name = "memory")
class MemoryEntity(
    name: String,
    imageUrl: String,
    manufacturer: String,
    type: String,
    capacity: Int,
    height: Int
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
    var type: String = type
        protected set

    @Column(nullable = false)
    var capacity: Int = capacity
        protected set

    @Column(nullable = false)
    var height: Int = height
        protected set

    fun update(memoryDTO: MemoryDTO) {
        this.name = memoryDTO.name
        if(memoryDTO.imageUrl!=null)
            this.imageUrl = memoryDTO.imageUrl
        this.manufacturer = memoryDTO.manufacturer
        this.type = memoryDTO.type
        this.capacity = memoryDTO.capacity
        this.height = memoryDTO.height
    }
}
