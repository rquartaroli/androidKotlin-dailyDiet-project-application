package com.example.dailydietapplication.domain.usecase

import com.example.dailydietapplication.data.dto.Meal
import com.example.dailydietapplication.domain.repository.MealRepository
import javax.inject.Inject

class GetMealsUseCase @Inject constructor (
    private val mealRepository: MealRepository
    ) {

    suspend fun getMeals(): MutableList<Meal> {
        val listMeals: MutableList<Meal> = mutableListOf()
        return try {
            mealRepository.getMeals()
        }catch (errorGetMeals: Exception) {
            errorGetMeals.printStackTrace()
            listMeals
        }
    }
}