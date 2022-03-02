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

    lateinit var binding: ActivityGameSettingBinding
    lateinit var operator: String
    var isSetA = false
    var isSetB = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameSettingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnGoToGame.setOnClickListener {


            if (binding.startRangeA.text.isNullOrBlank() &&
                binding.startRangeB.text.isNullOrBlank() &&
                binding.endRangeA.text.isNullOrBlank() &&
                binding.endRangeB.text.isNullOrBlank() &&
                binding.edChooseOperator.text.isNullOrBlank()
            ) {
                Toast.makeText(this, " At least please fill the ranges", Toast.LENGTH_SHORT).show()
            } else {
                val inputOperator = binding.edChooseOperator.text
                if (inputOperator.isNullOrBlank()) {
                    Game.operator = "%"
                } else if (inputOperator.matches(Regex("^[*]|[-]|[+]|[/]|[%]$"))) {
                    Game.operator = inputOperator.toString()
                }
                try {
                    if (binding.startRangeA.text.isNullOrBlank()||
                         binding.endRangeA.text.isNullOrBlank()||
                            binding.startRangeB.text.isNullOrBlank()||
                        binding.startRangeB.text.isNullOrBlank())
                    {
                        Toast.makeText(this, "please fill all the ranges", Toast.LENGTH_SHORT).show()
                    }
                       else {
                        if (binding.startRangeA.text.toString().toInt() < binding.endRangeA.text.toString().toInt() && !binding.startRangeA.text.isNullOrBlank() && !binding.endRangeA.text.isNullOrBlank()) {
                            Game.startRangA = binding.startRangeA.text.toString()
                            Game.endRangA = binding.endRangeA.text.toString()
                            isSetA = true
                        } else {
                            binding.startRangeA.error = "inValid range"
                            binding.endRangeA.error = "inValid range"
                        }

                        if (binding.startRangeB.text.toString().toInt() < binding.endRangeB.text.toString().toInt() && !binding.startRangeB.text.isNullOrBlank() && !binding.endRangeB.text.isNullOrBlank()) {
                            Game.startRangB = binding.startRangeB.text.toString()
                            Game.endRangB = binding.endRangeB.text.toString()
                            isSetB = true
                        } else {
                            binding.startRangeB.error = "inValid range"
                            binding.endRangeB.error = "inValid range"
                        }
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "invalid", Toast.LENGTH_SHORT).show()
                }

                if (isSetA && isSetB) {
                    val intent = Intent(this, GameActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}