package com.example.dailydietapplication.data.api

import com.example.dailydietapplication.data.dto.MealToAPI
import com.example.dailydietapplication.data.dto.Meals
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MealAPI {

    @GET("meals")
    suspend fun fetchMealsFromAPI() : Response<Meals>

    @POST("meals")
    suspend fun sendMealToAPI(
        @Body meal: MealToAPI
    ) : Response<Unit>

    @PUT("meals/{id}")
    suspend fun updateMealToAPI(
        @Path("id") id: String,
        @Body meal: MealToAPI
    ) : Response<Unit>

    @DELETE("meals/{id}")
    suspend fun removeMeal(
        @Path("id") id: String,
    ) : Response<Unit>
}