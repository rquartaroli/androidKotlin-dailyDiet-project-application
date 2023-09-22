package com.example.dailydietapplication.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailydietapplication.data.dto.Meal
import com.example.dailydietapplication.data.dto.MealToAPI
import com.example.dailydietapplication.domain.usecase.GetMealsUseCase
import com.example.dailydietapplication.domain.usecase.RemoveMealUseCase
import com.example.dailydietapplication.domain.usecase.SetMealUseCase
import com.example.dailydietapplication.domain.usecase.UpdateMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val mealsUseCase: GetMealsUseCase,
    private val mealSetUseCase: SetMealUseCase,
    private val mealUpdateUseCase: UpdateMealUseCase,
    private val mealRemoveMealUseCase: RemoveMealUseCase,
): ViewModel() {
    private val _meals = MutableLiveData<MutableList<Meal>>()

    val meals: LiveData<MutableList<Meal>>
        get() = _meals

    init {
        getMeals()
    }

    private fun getMeals(){
        viewModelScope.launch {
            val listMeals = mealsUseCase.getMeals()
            _meals.postValue(listMeals)
        }
    }
    
    suspend fun setMeal(name: String, describe: String, its_in_diety: Boolean){
        val meal = MealToAPI(
            "-1",
            "-1",
            name,
            describe,
            "-1",
            its_in_diety
        )

        viewModelScope.launch {
            mealSetUseCase.setMeal(meal)
        }
    }

    suspend fun updateMeal(id: String, name: String, describe: String, its_in_diety: Boolean){
        val meal = MealToAPI(
            "-1",
            "-1",
            name,
            describe,
            "-1",
            its_in_diety
        )

        viewModelScope.launch {
            mealUpdateUseCase.updateMeal(id, meal)
        }
    }

    suspend fun removeMeal(id: String){
        viewModelScope.launch {
            mealRemoveMealUseCase.removeMeal(id)
        }
    }
}