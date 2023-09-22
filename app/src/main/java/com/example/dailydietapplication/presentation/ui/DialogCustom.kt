package com.example.dailydietapplication.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.dailydietapplication.R
import com.example.dailydietapplication.presentation.viewmodel.MealsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DialogCustom: DialogFragment() {

    private lateinit var mealsViewModel: MealsViewModel

    companion object {
        fun paramValues(id: String): DialogCustom {
            val args = Bundle()
            args.putString("id", id)
            val fragment = DialogCustom()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.dialog_custom_layout, container)

        mealsViewModel = ViewModelProvider(this)[MealsViewModel::class.java]

        val paramId = arguments?.getString("id")

        val buttonCancel = view.findViewById<Button>(R.id.buttonCancelRemove)
        val buttonConfirm = view.findViewById<Button>(R.id.buttonRemove)

        buttonCancel.setOnClickListener { dismiss() }
        buttonConfirm.setOnClickListener {
            if(paramId != null) {
                CoroutineScope(Dispatchers.IO).launch {
                    mealsViewModel.removeMeal(paramId)
                }

                val intent = Intent(view.context, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }

        return view
    }

}