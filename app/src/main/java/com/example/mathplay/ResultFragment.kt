package com.example.mathplay

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.mathplay.databinding.ActivityResultBinding
import com.example.mathplay.databinding.FragmentResultBinding
import kotlin.system.exitProcess

class ResultFragment : Fragment() {
    lateinit var binding: FragmentResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        initViews()
        setListeners()
        return  binding.root
    }
    fun initViews(){
        binding.tvScoreRsult.text = Game.score.toString()
        binding.maxScoreAdded.text = Game.max().toString()
    }

    fun setListeners(){
        binding.btnReset.setOnClickListener {
            resetGame()
            findNavController().navigate(R.id.action_resultFragment_to_gameFragment)
        }

        binding.btnFinish.setOnClickListener {
           // moveTaskToBack(true)
            exitProcess(-1)
        }
    }

    private fun resetGame() {
        Game.score = 0
        Game.level = 0
    }

    private fun createScoreStream():String{
        var scoreStream=""
        for (score in Game.scoreList)
            scoreStream+="$score  "
        return scoreStream
    }
}