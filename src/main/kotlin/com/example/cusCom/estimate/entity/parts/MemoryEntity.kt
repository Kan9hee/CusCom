package com.example.cusCom.estimate.dto.parts

import jakarta.persistence.*

@Table(name="memory")
@Entity
class MemoryEntity(name:String,
                   manufacturer:String,
                   type:String,
                   capacity:Int,
                   height:Int){
    @Id
    @Column(nullable = false)
    var name:String=name
    @Column(nullable = false)
    var manufacturer:String=manufacturer
    @Column(nullable = false)
    var type:String=type
    @Column(nullable = false)
    var capacity:Int=capacity
    @Column(nullable = false)
    var height:Int=height

    fun update(memory:Memory){
        this.name=memory.name
        this.manufacturer=memory.manufacturer
        this.type=memory.type
        this.capacity=memory.capacity
        this.height=memory.height
    }
}
