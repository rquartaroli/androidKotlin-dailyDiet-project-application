package com.example.dailydietapplication.presentation.ui

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import androidx.core.content.ContextCompat
import com.example.dailydietapplication.R
import com.example.dailydietapplication.utils.StatusBarTransparent.Companion.setLightStatusBarAndTransparentStatusBar
import com.example.dailydietapplication.databinding.ActivityCongratulationBinding

class CongratulationActivity : AppCompatActivity() {

    private val binding by lazy {
      ActivityCongratulationBinding.inflate( layoutInflater )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setLightStatusBarAndTransparentStatusBar()

        val bundle = intent.extras

        if(bundle != null) {
            val isDiet = bundle.getBoolean("isDiet")

            if (isDiet) {
                val phraseCongratulation = resources.getString(R.string.congratulation_message)
                val builder = SpannableStringBuilder(phraseCongratulation)
                val start = phraseCongratulation.indexOf("dentro")
                val end = start + "dentro da dieta.".length
                val phraseFormated = builder.apply {
                    setSpan(StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }

                binding.congratulationPhrase.text = phraseFormated
            } else {
                val phraseCongratulation = resources.getString(R.string.shame_message)
                val builder = SpannableStringBuilder(phraseCongratulation)
                val start = phraseCongratulation.indexOf("saiu")
                val end = start + "saiu da dieta".length
                val phraseFormated = builder.apply {
                    setSpan(StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }

                binding.congratulationPhrase.text = phraseFormated
                binding.messageMotivational.text = resources.getString(R.string.shame)
                binding.messageMotivational.setTextColor(ContextCompat.getColor(this,
                    R.color.red_dark
                ))
                binding.imageCongratulation.setImageResource(R.drawable.img_shame)
            }
        }

        binding.buttonGoHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }
}