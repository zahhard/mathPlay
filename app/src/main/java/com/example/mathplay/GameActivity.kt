package com.example.mathplay

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Range
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.mathplay.databinding.ActivityGameBinding
import kotlin.random.Random
import kotlin.random.nextInt

class GameActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameBinding
    var buttons = ArrayList<Button>()
    lateinit var countDownTimer : CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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

//            buttonInit()
//            binding.tvRandom1.text = Game.a.toString()
//            binding.tvRandom2.text = Game.b.toString()
//            setValueToButtons()
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
            var intent = Intent(this, resultActivty::class.java)
            intent.putExtra("score", Game.score)
            startForResult.launch(intent)
        } else {
            binding.tvRandom1.text = Game.a.toString()
            binding.tvRandom2.text = Game.b.toString()
            Game.randomOption()
            setValueToButtons()
            for (i in 0..3) {
                buttons[i].setBackgroundColor(ContextCompat.getColor(this, R.color.orange))
            }
            countDownTimer.start()
        }
    }

    fun buttonInit() {
        buttons.add(binding.btnOption1)
        buttons.add(binding.btnOption2)
        buttons.add(binding.btnOption3)
        buttons.add(binding.btnOption4)
    }

    fun setValueToButtons() {
        var endRange = (Game.b) * 2
        if (Game.operator == "*")
            endRange = (Game.b) * (Game.b)

        for (i in 0..3){
            if (Game.randomIndex == i){
                buttons[i].text = Game.result.toString()
            }
            else
                buttons[i].text = Random.nextInt((Game.a)..endRange).toString()
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
