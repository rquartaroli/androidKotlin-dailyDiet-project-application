package com.example.dailydietapplication.data.repository

import android.util.Log
import com.example.dailydietapplication.data.api.MealAPI
import com.example.dailydietapplication.data.dto.Meal
import com.example.dailydietapplication.data.dto.MealToAPI
import com.example.dailydietapplication.data.dto.Meals
import com.example.dailydietapplication.domain.repository.MealRepository
import retrofit2.Response
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor (
    private val mealAPI: MealAPI
    ) : MealRepository {
    override suspend fun getMeals(): MutableList<Meal> {
        val listMeals: MutableList<Meal> = mutableListOf()
        var res: Response<Meals>? = null

        try {
            res = mealAPI.fetchMealsFromAPI()

            if (res != null) {
                if(res.isSuccessful) {
                    val fetchMeals = res.body()

                    if(fetchMeals != null) {
                        return fetchMeals.meals
                    }

                }
            }
            return listMeals
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_meal", "erro ao tentar recuperar as refeições")
            return listMeals
        }
    }

    override suspend fun setMeal(meal: MealToAPI) {
        var res: Response<Unit>? = null

        try {
            res = mealAPI.sendMealToAPI(meal)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_succefully", "erro ao tentar enviar a refeição")
        }

        if (res != null) {
            if(res.isSuccessful) {
                Log.i("info_succefully", "HTTP RESPONSE: $res")
                Log.i("info_succefully", "HTTP RESPONSE CODE: ${res.code()}")
            }
        }
    }

    override suspend fun updateMeal(id: String, meal: MealToAPI) {
        var res: Response<Unit>? = null

        try {
            res = mealAPI.updateMealToAPI(
                id,
                meal
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_succefully", "erro ao tentar editar a refeição")
        }

        if (res != null) {
            if(res.isSuccessful) {
                Log.i("info_succefully", "HTTP RESPONSE: $res")
                Log.i("info_succefully", "HTTP RESPONSE CODE: ${res.code()}")
            }
        }
    }

    override suspend fun removeMeal(id: String) {
        var res: Response<Unit>? = null

        try {
            res = mealAPI.removeMeal(id)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_succefully", "erro ao tentar remover as refeições")
        }

        if (res != null) {
            if(res.isSuccessful) {
                Log.i("info_succefully", "HTTP RESPONSE: $res")
                Log.i("info_succefully", "HTTP RESPONSE CODE: ${res.code()}")
            }
        }
    }
}