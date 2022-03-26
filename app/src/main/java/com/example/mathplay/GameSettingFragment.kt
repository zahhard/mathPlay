package com.example.mathplay

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mathplay.databinding.FragmentGameSetting2Binding

class GameSettingFragment : Fragment() {
    lateinit var binding : FragmentGameSetting2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameSetting2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                findNavController().navigate(R.id.action_gameSettingFragment_to_gameFragment)
                setOperator()

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
            Toast.makeText(activity, "please fill all fields", Toast.LENGTH_SHORT).show()
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