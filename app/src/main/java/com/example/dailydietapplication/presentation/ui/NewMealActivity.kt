package com.example.dailydietapplication.presentation.ui

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.dailydietapplication.R
import com.example.dailydietapplication.utils.StatusBarTransparent.Companion.setLightStatusBarAndTransparentStatusBar
import com.example.dailydietapplication.databinding.ActivityNewMealBinding
import com.example.dailydietapplication.presentation.viewmodel.MealsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewMealActivity : AppCompatActivity() {

    private lateinit var mealsViewModel: MealsViewModel

    private val binding by lazy {
        ActivityNewMealBinding.inflate( layoutInflater)
    }

    private var buttonIsDiet: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setLightStatusBarAndTransparentStatusBar()

        mealsViewModel = ViewModelProvider(this)[MealsViewModel::class.java]

        val bundle = intent.extras

        if (bundle != null) {
            val idMeal = bundle.getString("idMeal")
            val sessionMeal = bundle.getString("sessionMeal")
            val nameMeal = bundle.getString("nameMeal")
            val descriptionMeal = bundle.getString("descriptionMeal")
            val dateMeal = bundle.getString("dateMeal")
            val itsInDiet = bundle.getInt("itsInDiet")

            with(binding) {
                screenNewMealIconGoBack.setOnClickListener {
                    finish()
                }
                titleHeader.text = resources.getString(R.string.edit_meal)
                inputName.setText(nameMeal)
                inputDescription.setText(descriptionMeal)
                buttonRegisterMeal.text = resources.getString(R.string.button_save_edit)

                val defaultBgColor: Drawable? = ContextCompat.getDrawable(root.context,
                    R.drawable.shape_gray_rectangle
                )
                if(itsInDiet == 1) {
                    val changeShapeBg: Drawable? = ContextCompat.getDrawable(root.context,
                        R.drawable.ripple_effect_button_yes
                    )
                    frameButtonYes.background = changeShapeBg
                    frameButtonNo.background = defaultBgColor
                    buttonIsDiet = true
                } else {
                    val changeShapeBg: Drawable? = ContextCompat.getDrawable(root.context,
                        R.drawable.ripple_effect_button_no
                    )
                    frameButtonNo.background = changeShapeBg
                    frameButtonYes.background = defaultBgColor
                    buttonIsDiet = false
                }
                frameButtonYes.setOnClickListener {
                    val changeShapeBg: Drawable? = ContextCompat.getDrawable(root.context,
                        R.drawable.ripple_effect_button_yes
                    )
                    frameButtonYes.background = changeShapeBg
                    frameButtonNo.background = defaultBgColor
                    buttonIsDiet = true
                }

                frameButtonNo.setOnClickListener {
                    val changeShapeBg: Drawable? = ContextCompat.getDrawable(root.context,
                        R.drawable.ripple_effect_button_no
                    )
                    frameButtonNo.background = changeShapeBg
                    frameButtonYes.background = defaultBgColor
                    buttonIsDiet = false
                }
                buttonRegisterMeal.setOnClickListener {
                    val name = inputName.text.toString()
                    val description = inputDescription.text.toString()

                    if(name == "" || description == "") {
                        Toast.makeText(root.context, "Todos os campos precisam ser preenchidos", Toast.LENGTH_SHORT).show()
                    } else {
                        if(buttonIsDiet != null) {
                            CoroutineScope(Dispatchers.IO).launch {
                                mealsViewModel.updateMeal(idMeal!!, name, description, buttonIsDiet!!)
                            }

                            val intent = Intent(root.context, CongratulationActivity::class.java)
                            intent.putExtra("isDiet", buttonIsDiet)
                            startActivity(intent)
                        } else {
                            Toast.makeText(root.context, "Você precisa selecionar se esta na dieta ou não", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }
        } else {
            with(binding) {
                screenNewMealIconGoBack.setOnClickListener {
                    finish()
                }

                val defaultBgColor: Drawable? = ContextCompat.getDrawable(root.context,
                    R.drawable.shape_gray_rectangle
                )
                frameButtonYes.setOnClickListener {
                    val changeShapeBg: Drawable? = ContextCompat.getDrawable(root.context,
                        R.drawable.ripple_effect_button_yes
                    )
                    frameButtonYes.background = changeShapeBg
                    frameButtonNo.background = defaultBgColor
                    buttonIsDiet = true
                }

                frameButtonNo.setOnClickListener {
                    val changeShapeBg: Drawable? = ContextCompat.getDrawable(root.context,
                        R.drawable.ripple_effect_button_no
                    )
                    frameButtonNo.background = changeShapeBg
                    frameButtonYes.background = defaultBgColor
                    buttonIsDiet = false
                }

                buttonRegisterMeal.setOnClickListener {
                    val name = inputName.text.toString()
                    val description = inputDescription.text.toString()

                    if(name == "" || description == "") {
                        Toast.makeText(root.context, "Todos os campos precisam ser preenchidos", Toast.LENGTH_SHORT).show()
                    } else {
                        if(buttonIsDiet != null) {
                            CoroutineScope(Dispatchers.IO).launch {
                                mealsViewModel.setMeal(name, description, buttonIsDiet!!)
                            }

                            val intent = Intent(root.context, CongratulationActivity::class.java)
                            intent.putExtra("isDiet", buttonIsDiet)
                            startActivity(intent)
                        } else {
                            Toast.makeText(root.context, "Você precisa selecionar se esta na dieta ou não", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}