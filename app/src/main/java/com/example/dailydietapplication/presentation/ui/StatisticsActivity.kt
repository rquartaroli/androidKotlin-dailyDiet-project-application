package com.example.dailydietapplication.presentation.ui

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.dailydietapplication.R
import com.example.dailydietapplication.utils.StatusBarTransparent.Companion.setLightStatusBarAndTransparentStatusBar
import com.example.dailydietapplication.databinding.ActivityStatisticsBinding

class StatisticsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityStatisticsBinding.inflate( layoutInflater )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setLightStatusBarAndTransparentStatusBar()

        val bundle = intent.extras
        if( bundle != null) {
            val percentTotalInDiet = bundle.getString("percentTotalInDiet")
            val allMealsRegistered = bundle.getInt("mealsRegistered")
            val mealsInDiet = bundle.getInt("mealsInDiet")
            val mealsOutDiet = bundle.getInt("mealsOutDiet")
            val belowFifty = bundle.getBoolean("belowFifty")

            if(belowFifty) {
                binding.screenLinearLayout.setBackgroundResource(R.color.red_light)
                val drawable: Drawable? = binding.iconGoBack.drawable
                drawable?.colorFilter = android.graphics.PorterDuffColorFilter(ContextCompat.getColor(this,
                    R.color.red_dark
                ), android.graphics.PorterDuff.Mode.SRC_IN)
                binding.iconGoBack.setImageDrawable(drawable)
            } else {
                binding.screenLinearLayout.setBackgroundResource(R.color.green_light)
                val drawable: Drawable? = binding.iconGoBack.drawable
                drawable?.colorFilter = android.graphics.PorterDuffColorFilter(ContextCompat.getColor(this,
                    R.color.green_dark
                ), android.graphics.PorterDuff.Mode.SRC_IN)
                binding.iconGoBack.setImageDrawable(drawable)
            }

            with(binding) {
                textLargePercent.text = percentTotalInDiet
                mealsRegistered.text = allMealsRegistered.toString()
                percentMealsInDiet.text = mealsInDiet.toString()
                percentMealsOutDiet.text = mealsOutDiet.toString()
            }
        }

        binding.iconGoBack.setOnClickListener {
            finish()
        }
    }
}