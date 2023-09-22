package com.example.dailydietapplication.domain.usecase

import com.example.dailydietapplication.domain.repository.MealRepository
import javax.inject.Inject

class RemoveMealUseCase @Inject constructor(
    private val mealRepository: MealRepository
) {
    suspend fun removeMeal(id: String) {
        mealRepository.removeMeal(id)
    }
}