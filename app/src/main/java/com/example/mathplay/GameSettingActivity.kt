package com.example.mathplay

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mathplay.databinding.ActivityGameSettingBinding

class GameSettingActivity : AppCompatActivity() {

    lateinit var binding: ActivityGameSettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameSettingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnGoToGame.setOnClickListener {
            if (areValidFields()) {
                Game.startRangA = binding.startRangeA.text.toString()
                Game.endRangA = binding.endRangeA.text.toString()
                Game.startRangB = binding.startRangeB.text.toString()
                Game.endRangB = binding.endRangeB.text.toString()
                Game.operator = binding.edChooseOperator.text.toString()
                val intent = Intent(this, GameActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun areValidFields(): Boolean {
        if (binding.startRangeA.text.isNullOrBlank() &&
            binding.startRangeB.text.isNullOrBlank() &&
            binding.endRangeA.text.isNullOrBlank() &&
            binding.endRangeB.text.isNullOrBlank() &&
            binding.edChooseOperator.text.isNullOrBlank()
        ) {
            Toast.makeText(this, "please fill all fields", Toast.LENGTH_SHORT).show()
            return false
        }

        if (binding.startRangeA.text.isNullOrBlank() || binding.endRangeA.text.isNullOrBlank()) {
            binding.startRangeA.error = "fill both range"
            binding.endRangeA.error = "fill both range"
            return false
        }

        if (binding.startRangeA.text.toString().toInt() >= binding.endRangeA.text.toString()
                .toInt()
        ) {
            binding.startRangeA.error = "invalid range"
            binding.endRangeA.error = "invalid range"
            return false
        }

        if (binding.startRangeB.text.isNullOrBlank() || binding.endRangeB.text.isNullOrBlank()) {
            binding.startRangeB.error = "fill both range"
            binding.endRangeB.error = "fill both range"
            return false
        }

        if (binding.startRangeB.text.toString().toInt() >= binding.endRangeB.text.toString()
                .toInt()
        ) {
            binding.startRangeB.error = "invalid range"
            binding.endRangeB.error = "invalid range"
            return false
        }

        val inputOperator = binding.edChooseOperator.text
        if (inputOperator.isNullOrBlank() || !inputOperator.matches(Regex("^[*]|[-]|[+]|[/]|[%]$"))) {
            Toast.makeText(this, " insert an oprator between /*%+-", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}