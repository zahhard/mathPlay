package com.example.mathplay

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Binder
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

        binding.tvOperator.text = Game.operator
        buttonInit()
        Game.nextLevel()
        binding.tvRandom1.text = Game.a.toString()
        binding.tvRandom2.text = Game.b.toString()
        Game.randomOption()
        setValueToButtons()

        binding.btnDice.setOnClickListener{
            initView()
            for (i in 0..3){
                buttons[i].isClickable = true
            }

        }

        binding.tvScore.text = Game.score.toString()
        for (i in 0..3) {
            buttons[i].setOnClickListener {
                var isCorrect = false
                for (j in 0..3) {
                    if (j == (Game.randomIndex)) {
                        buttons[j].setBackgroundColor(Color.GREEN)
                    } else {
                        buttons[j].setBackgroundColor(Color.RED)
                    }
                    buttons[j].isClickable = false
                }

                if (i == Game.randomIndex){
                    isCorrect = true
                }
                if (!isCorrect) {
                    Game.score -= 2
                } else {
                    Game.score += 5
                }
                binding.tvScore.text = Game.score.toString()
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
                if (Game.level<=5) {
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
        Game.nextLevel()
        if (Game.level > 5) {
            findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
            Game.scoreList.add(Game.score)
        } else {
            binding.tvRandom1.text = Game.a.toString()
            binding.tvRandom2.text = Game.b.toString()
            Game.randomOption()
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
        var endRange = (Game.a) * 2
        if (Game.operator == "*")
            endRange = (Game.a) * (Game.b)

        for (i in 0..3){
            if (Game.randomIndex == i){
                buttons[i].text = Game.result.toString()
            }
            else{
                if (Game.b < endRange)
                    buttons[i].text = Random.nextInt((Game.b)..endRange).toString()
                else
                    buttons[i].text = Random.nextInt(endRange..(Game.b)).toString()
            }

        }
    }

    fun reset() {
        Game.score = 0
        Game.level = 0
        initView()
        binding.tvScore.text = Game.score.toString()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("score", Game.score)
        outState.putInt("randomIndex", Game.randomIndex)
        outState.putInt("a", Game.a)
        outState.putInt("b", Game.b)
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