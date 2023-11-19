package com.example.cusCom.provideContent.entity.mySQL

import jakarta.persistence.*

@Table(name="\${dbString.mysql.table.cpuSocket}")
@Entity
class CPUSocketEntity(name:String,
                      availableChipsets:Array<String>) {
    @Id
    @Column(nullable = false)
    val name:String=name
    @ElementCollection
    val availableChipsets:Array<String> =availableChipsets
}