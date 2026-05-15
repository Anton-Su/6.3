package com.example.a63.domain.usecase

import com.example.a63.domain.model.DomainModel
import com.example.a63.domain.repository.Repository

/**
 * SaveDataUseCase - usecase для сохранения данных
 */
class SaveDataUseCase(private val repository: Repository) {
    suspend operator fun invoke(data: DomainModel) {
        repository.saveData(data)
    }
}

