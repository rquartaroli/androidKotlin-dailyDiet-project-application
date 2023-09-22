package com.example.dailydietapplication.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.dailydietapplication.R
import com.example.dailydietapplication.utils.StatusBarTransparent.Companion.setLightStatusBarAndTransparentStatusBar
import com.example.dailydietapplication.databinding.ActivityDetailMealBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class DetailMealActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityDetailMealBinding.inflate( layoutInflater )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setLightStatusBarAndTransparentStatusBar()

        val bundle = intent.extras

        if(bundle != null) {
            val idMeal = bundle.getString("idMeal")
            val sessionMeal = bundle.getString("sessionMeal")
            val nameMeal = bundle.getString("nameMeal")
            val descriptionMeal = bundle.getString("descriptionMeal")
            val dateMeal = bundle.getString("dateMeal")
            val itsInDiet = bundle.getInt("itsInDiet")

            with(binding) {
                buttonGoBack.setOnClickListener {
                    finish()
                }
                binding.nameMeal.text = nameMeal
                binding.descriptionMeal.text = descriptionMeal

                val getFormatDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                val hourFormatNeeded = SimpleDateFormat("dd/MM/yyyy 'às' HH:mm", Locale.getDefault())
                val stringForDate = getFormatDate.parse(dateMeal)

                val formatedHour = hourFormatNeeded.format(stringForDate)

                binding.dateMeal.text = formatedHour

                if (itsInDiet == 1) {
                    screenDetailMeal.setBackgroundColor(ContextCompat.getColor(root.context,
                        R.color.green_light
                    ))
                    tagShapeCircle.setCardBackgroundColor(ContextCompat.getColor(root.context,
                        R.color.green_dark
                    ))
                    tagDiet.text = resources.getString(R.string.tag_in_diet)
                } else {
                    screenDetailMeal.setBackgroundColor(ContextCompat.getColor(root.context,
                        R.color.red_light
                    ))
                    tagShapeCircle.setCardBackgroundColor(ContextCompat.getColor(root.context,
                        R.color.red_dark
                    ))
                    tagDiet.text = resources.getString(R.string.tag_out_diet)
                }

                buttonEditMeal.setOnClickListener {
                    val intent = Intent(root.context, NewMealActivity::class.java)
                    intent.putExtra("idMeal", idMeal)
                    intent.putExtra("sessionMeal", sessionMeal)
                    intent.putExtra("nameMeal", nameMeal)
                    intent.putExtra("descriptionMeal", descriptionMeal)
                    intent.putExtra("dateMeal", dateMeal)
                    intent.putExtra("itsInDiet", itsInDiet)

                    startActivity(intent)
                }

                buttonRemoveMeal.setOnClickListener {
                    val dialog = DialogCustom.paramValues(idMeal!!)
                    dialog.show(supportFragmentManager, dialog.tag)
                }
            }
        }
    }
}