package com.example.dailydietapplication.data.dto

data class Meal(
    val id: String?,
    val session_id: String?,
    val name: String,
    val describe: String,
    val created_at: String?,
    val its_in_diety: Int,
)

data class Meals(val meals: MutableList<Meal>)

data class MealToAPI(
    val id: String?,
    val session_id: String?,
    val name: String,
    val describe: String,
    val created_at: String?,
    val its_in_diety: Boolean,
)
