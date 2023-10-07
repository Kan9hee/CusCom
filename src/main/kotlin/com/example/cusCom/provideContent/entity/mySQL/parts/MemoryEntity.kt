package com.example.cusCom.provideContent.entity.mySQL.parts

import com.example.cusCom.provideContent.dto.parts.Memory
import jakarta.persistence.*

@Table(name="memory")
@Entity
class MemoryEntity(memory: Memory){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    val id:Long=0

    @Column(nullable = false)
    var name:String=memory.name
        protected set

    @Column(nullable=false)
    var imageUrl:String=memory.imageUrl
        protected set

    @Column(nullable = false)
    var manufacturer:String=memory.manufacturer
        protected set

    @Column(nullable = false)
    var type:String=memory.type
        protected set

    @Column(nullable = false)
    var capacity:Int=memory.capacity
        protected set

    @Column(nullable = false)
    var height:Int=memory.height
        protected set

    fun update(memory: Memory){
        this.name=memory.name
        this.imageUrl=memory.imageUrl
        this.manufacturer=memory.manufacturer
        this.type=memory.type
        this.capacity=memory.capacity
        this.height=memory.height
    }
}
