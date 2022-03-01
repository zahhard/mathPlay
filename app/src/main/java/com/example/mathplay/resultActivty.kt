package com.example.mathplay

import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mathplay.databinding.ActivityResultBinding
import kotlin.system.exitProcess

class resultActivty : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var score = intent.getIntExtra("score", -1)
        Game.scoreList.add(score)
        binding.tvScoreRsult.text = score.toString()
        binding.maxScoreAdded.text = Game.max().toString()

        binding.btnReset.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("isReset" , true)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        binding.btnFinish.setOnClickListener {
            moveTaskToBack(true)
            exitProcess(-1)
        }

    }
}