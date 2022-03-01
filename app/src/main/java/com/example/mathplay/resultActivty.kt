package com.example.mathplay

import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mathplay.databinding.ActivityMainBinding
import com.example.mathplay.databinding.ActivityResultBinding

class resultActivty : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var score = intent.getIntExtra("score", -1)
        binding.tvScoreRsult.text = score.toString()

        binding.btnReset.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("isReset" , true)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

    }
}