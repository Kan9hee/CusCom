package com.example.cusCom.estimate.service.parts

import com.example.cusCom.estimate.repository.parts.MemoryRepository
import org.springframework.stereotype.Service

@Service
class MemoryService(private val memoryRepo:MemoryRepository) {
}