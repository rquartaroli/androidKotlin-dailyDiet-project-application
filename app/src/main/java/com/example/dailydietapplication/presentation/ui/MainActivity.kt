package com.example.dailydietapplication.presentation.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dailydietapplication.*
import com.example.dailydietapplication.utils.StatusBarTransparent.Companion.setLightStatusBarAndTransparentStatusBar
import com.example.dailydietapplication.data.dto.Meal
import com.example.dailydietapplication.databinding.ActivityMainBinding
import com.example.dailydietapplication.presentation.viewmodel.MealsViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.text.DecimalFormat
import kotlin.math.round

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mealsViewModel: MealsViewModel

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }

    private lateinit var mealAdapter: MealDietAdapter

    private var percentTotalInDiet: String = "00.00%"
    private var belowFifty: Boolean = false
    private var mealInDiet: List<Meal> = emptyList()
    private var mealOutDiet: List<Meal> = emptyList()
    private var percentMeal: Double = 0.0
    private var percentFormated: Double = 0.0

    private val listMeals = mutableListOf<Meal>()

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.hide()

        setLightStatusBarAndTransparentStatusBar()

        mealsViewModel = ViewModelProvider(this)[MealsViewModel::class.java]

        mealsViewModel.meals.observe(this){mutableListMeals ->

            mutableListMeals.forEach { meal ->
                listMeals.add(meal)
            }

            val context = applicationContext

            mealInDiet = listMeals.filter { it.its_in_diety == 1 }
            mealOutDiet = listMeals.filter { it.its_in_diety != 1 }
            percentMeal = ((mealInDiet.size * 100).toDouble() / listMeals.size.toDouble())
            percentFormated = (round(percentMeal * 100) / 100.0)

                mealAdapter.getListMeal(listMeals)

                if(percentFormated < 50) {
                    binding.cardPercentMeals.setCardBackgroundColor(ContextCompat.getColor(context,
                        R.color.red_light
                    ))
                    val drawable: Drawable? = binding.iconViewPercentMeal.drawable
                    drawable?.colorFilter = android.graphics.PorterDuffColorFilter(ContextCompat.getColor(context,
                        R.color.red_dark
                    ), android.graphics.PorterDuff.Mode.SRC_IN)
                    binding.iconViewPercentMeal.setImageDrawable(drawable)
                    belowFifty = true
                } else {
                    binding.cardPercentMeals.setCardBackgroundColor(ContextCompat.getColor(context,
                        R.color.green_light
                    ))
                    val drawable: Drawable? = binding.iconViewPercentMeal.drawable
                    drawable?.colorFilter = android.graphics.PorterDuffColorFilter(ContextCompat.getColor(context,
                        R.color.green_dark
                    ), android.graphics.PorterDuff.Mode.SRC_IN)
                    binding.iconViewPercentMeal.setImageDrawable(drawable)
                    belowFifty = false
                }

                val formatedRes = DecimalFormat("#0.00").format(percentFormated)
                percentTotalInDiet = formatedRes.toString().replace('.', ',') + "%"

                binding.percentDietLarge.text = percentTotalInDiet
        }

        CoroutineScope(Dispatchers.IO).launch {
            chargeImage()
        }

        mealAdapter = MealDietAdapter{meal ->
            val intent = Intent(this, DetailMealActivity::class.java)
            intent.putExtra("idMeal", meal.id)
            intent.putExtra("sessionMeal", meal.session_id)
            intent.putExtra("nameMeal", meal.name)
            intent.putExtra("descriptionMeal", meal.describe)
            intent.putExtra("dateMeal", meal.created_at)
            intent.putExtra("itsInDiet", meal.its_in_diety)

            startActivity(intent)
        }
        mealAdapter.getListMeal(listMeals)

        binding.rvMealDiet.adapter = mealAdapter
        binding.rvMealDiet.layoutManager = LinearLayoutManager(this)

        binding.cardPercentMeals.setOnClickListener {
            val intent = Intent(this, StatisticsActivity::class.java)
            intent.putExtra("percentTotalInDiet", percentTotalInDiet)
            intent.putExtra("mealsRegistered", listMeals.size)
            intent.putExtra("mealsInDiet", mealInDiet.size)
            intent.putExtra("mealsOutDiet", mealOutDiet.size)
            intent.putExtra("belowFifty", belowFifty)

            startActivity(intent)
        }

        binding.buttonNewMeal.setOnClickListener {
            val intent = Intent(this, NewMealActivity::class.java)
            startActivity(intent)
        }
    }

    private suspend fun chargeImage() {
        withContext(Dispatchers.Main) {
            Picasso.get()
                .load("https://avatars.githubusercontent.com/u/48213007?v=4")
                .into(binding.perfilImage)
        }
    }
}