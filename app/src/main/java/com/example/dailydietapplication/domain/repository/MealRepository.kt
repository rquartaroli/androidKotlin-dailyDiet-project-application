package com.example.dailydietapplication.domain.repository

import com.example.dailydietapplication.data.dto.Meal
import com.example.dailydietapplication.data.dto.MealToAPI
import com.example.dailydietapplication.data.dto.Meals
import retrofit2.Response

interface MealRepository {
    suspend fun getMeals(): MutableList<Meal>
    suspend fun setMeal(meal: MealToAPI)
    suspend fun updateMeal(id: String, meal: MealToAPI)
    suspend fun removeMeal(id: String)
}