package com.example.dailydietapplication.domain.usecase

import com.example.dailydietapplication.data.dto.MealToAPI
import com.example.dailydietapplication.domain.repository.MealRepository
import javax.inject.Inject

class SetMealUseCase @Inject constructor(
    private val mealRepository: MealRepository
) {
    suspend fun setMeal(meal: MealToAPI) {
        mealRepository.setMeal(meal)
    }
}