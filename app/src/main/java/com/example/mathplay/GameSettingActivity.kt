package com.example.mathplay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Range
import android.widget.Toast
import com.example.mathplay.databinding.ActivityGameBinding
import com.example.mathplay.databinding.ActivityGameSettingBinding
import java.awt.font.NumericShaper
import java.lang.Exception

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

            if (binding.startRangeA.text.isNullOrBlank() &&
                binding.startRangeB.text.isNullOrBlank() &&
                binding.endRangeA.text.isNullOrBlank() &&
                binding.endRangeB.text.isNullOrBlank() &&
                binding.edChooseOperator.text.isNullOrBlank()){
                Game.operator = "%"
                startActivity(intent)
            }
            if (inputOperator.matches(Regex("^[*]|[-]|[+]|[/]|[%]$"))){
                operator = inputOperator.toString()
                intent.putExtra("operator", operator)
            }
            try {
                if (binding.startRangeA.text.toString() < binding.endRangeA.text.toString() &&
                    !binding.startRangeA.text.isNullOrBlank()&&
                    !binding.endRangeA.text.isNullOrBlank()){
                    Game.startRangA = binding.startRangeA.text.toString()
                    Game.endRangA = binding.endRangeA.text.toString()
                    isSetA = true
                }
                else {
                    binding.startRangeA.error = "inValid range"
                    binding.endRangeA.error = "inValid range"
                }

                if (binding.startRangeB.text.toString() < binding.endRangeB.text.toString() &&
                    !binding.startRangeB.text.isNullOrBlank()&&
                    !binding.endRangeB.text.isNullOrBlank()){
                    Game.startRangB = binding.startRangeB.text.toString()
                    Game.endRangB = binding.endRangeB.text.toString()
                    isSetB = true
                }
                else {
                    binding.startRangeB.error = "inValid range"
                    binding.endRangeB.error = "inValid range"
                }
            }
            catch (e:Exception){
                Toast.makeText(this, "invalid", Toast.LENGTH_SHORT).show()
            }

            if (isSetA && isSetB)
                startActivity(intent)
        }
    }
}