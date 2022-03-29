package com.example.mathplay

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mathplay.databinding.FragmentGameBinding
import kotlin.random.Random
import kotlin.random.nextInt

class GameFragment : Fragment() {
    private lateinit var viewModel: ViewModel
    lateinit var binding : FragmentGameBinding
    var buttons = ArrayList<Button>()
    lateinit var countDownTimer : CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(viewModel::class.java)

        binding.tvOperator.text = GameRepository.operator
        buttonInit()
        viewModel.nextLevel()
        binding.tvRandom1.text = GameRepository.a.toString()
        binding.tvRandom2.text = GameRepository.b.toString()
        viewModel.randomOption()
        setValueToButtons()

        binding.btnDice.setOnClickListener{
            initView()
            for (i in 0..3){
                buttons[i].isClickable = true
            }

        }

        binding.tvScore.text = GameRepository.score.toString()
        for (i in 0..3) {
            buttons[i].setOnClickListener {
                var isCorrect = false
                for (j in 0..3) {
                    if (j == (GameRepository.randomIndex)) {
                        buttons[j].setBackgroundColor(Color.GREEN)
                    } else {
                        buttons[j].setBackgroundColor(Color.RED)
                    }
                    buttons[j].isClickable = false
                }

                if (i == GameRepository.randomIndex){
                    isCorrect = true
                }
                if (!isCorrect) {
                    GameRepository.score -= 2
                } else {
                    GameRepository.score += 5
                }
                binding.tvScore.text = GameRepository.score.toString()
            }
        }

        if (savedInstanceState != null) {
            binding.btnOption1.text = savedInstanceState.getString("button0")
            binding.btnOption2.text = savedInstanceState.getString("button1")
            binding.btnOption3.text = savedInstanceState.getString("button2")
            binding.btnOption4.text = savedInstanceState.getString("button3")

            binding.tvScore.text = savedInstanceState.getInt("score").toString()
            binding.tvRandom1.text = savedInstanceState.getInt("a").toString()
            binding.tvRandom2.text = savedInstanceState.getInt("b").toString()
        }

        countDownTimer = object : CountDownTimer(10000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                binding.timer.setText("Timer: " + millisUntilFinished / 1000)
            }

            override fun onFinish() {
                if (GameRepository.level<=5) {
                    initView()
                    for (i in 0..3) {
                        buttons[i].isClickable = true
                    }
                    countDownTimer.start()
                }

            }
        }
        countDownTimer.start()
    }

    fun initView() {
        viewModel.nextLevel()
        if (GameRepository.level > 5) {
            findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
            GameRepository.scoreList.add(GameRepository.score)
        } else {
            binding.tvRandom1.text = GameRepository.a.toString()
            binding.tvRandom2.text = GameRepository.b.toString()
            viewModel.randomOption()
            setValueToButtons()
            for (i in 0..3) {
                buttons[i].setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.orange))
            }
            countDownTimer.start()
        }
    }

    //Initialize of each buttons of each options
    fun buttonInit() {
        buttons.add(binding.btnOption1)
        buttons.add(binding.btnOption2)
        buttons.add(binding.btnOption3)
        buttons.add(binding.btnOption4)
    }

    private fun setValueToButtons() {
        var endRange = (GameRepository.a) * 2
        if (GameRepository.operator == "*")
            endRange = (GameRepository.a) * (GameRepository.b)

        for (i in 0..3){
            if (GameRepository.randomIndex == i){
                buttons[i].text = GameRepository.result.toString()
            }
            else{
                if (GameRepository.b < endRange)
                    buttons[i].text = Random.nextInt((GameRepository.b)..endRange).toString()
                else
                    buttons[i].text = Random.nextInt(endRange..(GameRepository.b)).toString()
            }

        }
    }

    fun reset() {
        GameRepository.score = 0
        GameRepository.level = 0
        initView()
        binding.tvScore.text = GameRepository.score.toString()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("score", GameRepository.score)
        outState.putInt("randomIndex", GameRepository.randomIndex)
        outState.putInt("a", GameRepository.a)
        outState.putInt("b", GameRepository.b)
        outState.putString("button0", buttons[0].text.toString())
        outState.putString("button1", buttons[1].text.toString())
        outState.putString("button2", buttons[2].text.toString())
        outState.putString("button3", buttons[3].text.toString())
        super.onSaveInstanceState(outState)
    }

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            val isReset = intent?.getBooleanExtra("isReset", false)
            if (isReset == true){
                reset()
                countDownTimer.start()
            }
        }
    }

}