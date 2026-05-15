package com.example.a63.domain.repository

import com.example.a63.domain.model.DomainModel

/**
 * Repository - интерфейс репозитория для слоя Domain
 */
interface Repository {
    suspend fun getData(): List<DomainModel>
    suspend fun saveData(data: DomainModel)
}

