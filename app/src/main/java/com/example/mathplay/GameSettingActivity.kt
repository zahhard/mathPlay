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
                var a = binding.endRangeA.text.toString()
                var b = binding.endRangeB.text.toString()

                if (b > a) {
                    Game.a = b.toInt()
                    Game.b = a.toInt()
                }
                else{
                    Game.a = a.toInt()
                    Game.b = b.toInt()
                }

                setOperator()
                val intent = Intent(this, GameActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setOperator(){
        if (binding.rbPlus.isChecked)
            Game.operator = "+"
        if (binding.rbMinus.isChecked)
            Game.operator = "-"
        if (binding.rbMulti.isChecked)
            Game.operator = "*"
        if (binding.rbDivide.isChecked)
            Game.operator = "/"
        if (binding.rbDivideRemaining.isChecked)
            Game.operator = "%"
    }

    private fun areValidFields(): Boolean {
        if (binding.endRangeA.text.isNullOrBlank() &&
            binding.endRangeB.text.isNullOrBlank()
        ) {
            Toast.makeText(this, "please fill all fields", Toast.LENGTH_SHORT).show()
            return false
        }

        if (binding.endRangeA.text.isNullOrBlank()) {
            binding.endRangeA.error = "fill range"
            return false
        }

        if (binding.endRangeB.text.isNullOrBlank()) {
            binding.endRangeB.error = "fill range"
            return false
        }
        return true
    }
}