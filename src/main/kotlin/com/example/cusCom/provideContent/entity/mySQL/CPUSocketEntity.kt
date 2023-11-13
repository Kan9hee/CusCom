package com.example.cusCom.provideContent.entity.mySQL

import jakarta.persistence.*

@Table(name="cpu_socket")
@Entity
class CPUSocketEntity(name:String,
                      availableChipsets:Array<String>) {
    @Id
    @Column(nullable = false)
    val name:String=name
    @ElementCollection
    val availableChipsets:Array<String> =availableChipsets
}