package com.example.mathplay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Range
import com.example.mathplay.databinding.ActivityGameBinding
import com.example.mathplay.databinding.ActivityGameSettingBinding
import java.awt.font.NumericShaper

class GameSettingActivity : AppCompatActivity() {

    lateinit var binding : ActivityGameSettingBinding
    lateinit var operator : String
    var isSetA   = false
    var isSetB   = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameSettingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnGoToGame.setOnClickListener {
            var intent = Intent(this, GameActivity::class.java)
            var inputOperator = binding.edChooseOperator.text
            if (inputOperator.matches(Regex("^[*]|[-]|[+]|[/]|[%]$"))){
                operator = inputOperator.toString()
            }
            if (binding.startRangeA.text.toString().toInt() < binding.endRangeA.text.toString().toInt()){
                Game.startRangA = binding.startRangeA.text.toString().toInt()
                Game.endRangA = binding.endRangeA.text.toString().toInt()
                isSetA = true
            }
            else {
                binding.startRangeA.error = "inValid range"
                binding.endRangeA.error = "inValid range"
            }

            if (binding.startRangeB.text.toString().toInt() < binding.endRangeB.text.toString().toInt()){
                Game.startRangB = binding.startRangeB.text.toString().toInt()
                Game.endRangB = binding.endRangeB.text.toString().toInt()
                isSetB = true
            }
            else {
                binding.startRangeB.error = "inValid range"
                binding.endRangeB.error = "inValid range"
            }
            if (binding.startRangeA.text.isNullOrBlank() &&
                binding.startRangeB.text.isNullOrBlank() &&
                binding.endRangeA.text.isNullOrBlank() &&
                binding.endRangeB.text.isNullOrBlank() &&
                binding.edChooseOperator.text.isNullOrBlank()){
                Game.operator = "%"
                startActivity(intent)
            }

            if (isSetA && isSetB)
                startActivity(intent)

        }
    }
}