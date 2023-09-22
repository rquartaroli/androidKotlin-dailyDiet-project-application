package com.example.dailydietapplication.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.dailydietapplication.data.dto.Meal
import com.example.dailydietapplication.R
import java.text.SimpleDateFormat
import java.util.*

class MealDietAdapter(
    private val clickActionItem: (Meal) -> Unit
): Adapter<MealDietAdapter.MealViewHolder>() {

    private var listMealDiet = mutableListOf<Meal>()
    private var datePrevious = ""

    fun getListMeal(fetchMeal: MutableList<Meal>) {
        listMealDiet.clear()
        listMealDiet.addAll(fetchMeal)
        notifyDataSetChanged()
    }

    inner class MealViewHolder(
        private val itemView: View
    ): ViewHolder(itemView) {
        private val textDateHeaderMeal: TextView = itemView.findViewById(R.id.textDateHeader)
        private val textHourMeal: TextView = itemView.findViewById(R.id.textHourItemMealDiet)
        private val textItemMeal: TextView = itemView.findViewById(R.id.textItemMealDiet)
        private val alertDietInOrOut: CardView = itemView.findViewById(R.id.alertDietInOrOut)
        private val itemViewLayout: FrameLayout = itemView.findViewById(R.id.itemFrameButton)

        var backgroundColorRed = ContextCompat.getColor(itemView.context, R.color.red_mid)
        var backgroundColorGreen = ContextCompat.getColor(itemView.context, R.color.green_mid)

        fun bind(meal: Meal) {
            val getFormatDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val hourFormatNeeded = SimpleDateFormat("HH:mm", Locale.getDefault())
            val dataFormatNeeded = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            val stringForDate = getFormatDate.parse(meal.created_at)

            val formatedHour = hourFormatNeeded.format(stringForDate)
            val formatedDataForVerify = dataFormatNeeded.format(stringForDate)

            if(datePrevious == formatedDataForVerify) {
                textDateHeaderMeal.visibility = View.GONE
            } else {
                datePrevious = formatedDataForVerify
                textDateHeaderMeal.text = datePrevious
                textDateHeaderMeal.visibility = View.VISIBLE
            }

            textHourMeal.text = formatedHour
            textItemMeal.text = meal.name

            if(meal.its_in_diety == 1) {
                alertDietInOrOut.setCardBackgroundColor(backgroundColorGreen)
            } else {
                alertDietInOrOut.setCardBackgroundColor(backgroundColorRed)
            }

            itemViewLayout.setOnClickListener {
                clickActionItem( meal )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val layoutInflate = LayoutInflater.from(parent.context)
        val itemView = layoutInflate.inflate(R.layout.item_rv_mealdiet, parent, false)

        return MealViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listMealDiet.size
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = listMealDiet[position]
        holder.bind(meal)
    }
}