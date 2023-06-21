package com.example.cusCom.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
data class Estimates(var cpu:String,
                     var motherBoard:String,
                     var memory:String,
                     var dataStorage:String,
                     var graphicsCard:String,
                     var cpuCooler:String,
                     var powerSupply:String,
                     var case:String){}
