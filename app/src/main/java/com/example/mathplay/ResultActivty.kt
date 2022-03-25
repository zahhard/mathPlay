package com.example.mathplay

import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mathplay.databinding.ActivityResultBinding
import kotlin.system.exitProcess

class ResultActivty : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val score = intent.getIntExtra("score", -1)
        Game.scoreList.add(score)

        initViews()
        setListeners()

    }
    fun initViews(){
        binding.tvScoreRsult.text = Game.score.toString()
        binding.maxScoreAdded.text = Game.max().toString()
    }

    fun setListeners(){
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
    private fun createScoreStream():String{
        var scoreStream=""
        for (score in Game.scoreList)
            scoreStream+="$score  "
        return scoreStream
    }
}