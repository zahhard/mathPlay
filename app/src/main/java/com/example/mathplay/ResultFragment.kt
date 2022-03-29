package com.example.mathplay

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mathplay.databinding.FragmentResultBinding
import kotlin.system.exitProcess

class ResultFragment : Fragment() {
    lateinit var binding: FragmentResultBinding
    private lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        initViews()
        setListeners()

    }
    fun initViews(){
        binding.tvScoreRsult.text = GameRepository.score.toString()
        binding.maxScoreAdded.text = viewModel.max().toString()
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
        GameRepository.score = 0
        GameRepository.level = 0
    }

    private fun createScoreStream():String{
        var scoreStream=""
        for (score in GameRepository.scoreList)
            scoreStream+="$score  "
        return scoreStream
    }
}