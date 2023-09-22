package com.example.dailydietapplication.domain.usecase

import com.example.dailydietapplication.data.dto.MealToAPI
import com.example.dailydietapplication.domain.repository.MealRepository
import javax.inject.Inject

class UpdateMealUseCase @Inject constructor(
    private val mealRepository: MealRepository
) {
    suspend fun updateMeal(id: String, meal: MealToAPI) {
        mealRepository.updateMeal(id, meal)
    }
}