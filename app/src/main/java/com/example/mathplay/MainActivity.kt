package com.example.mathplay

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.mathplay.databinding.ActivityMainBinding
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var buttons = ArrayList<Button>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        buttonInit()
        Game.nextLevel()
        binding.tvRandom1.text = Game.a.toString()
        binding.tvRandom2.text = Game.b.toString()
        Game.randomOption()
        setValueToButtons()


        binding.btnDice.setOnClickListener(::initView)

        binding.tvScore.text = Game.score.toString()
        for (i in 0..3){
            buttons[i].setOnClickListener {
                var isCorrect = false
                for (j in 0..3){
                    if (j == (Game.randomIndex)){
                        buttons[j].setBackgroundColor(Color.GREEN)
                        Game.score += 5
                        isCorrect = true
                    }
                    else
                    {
                        buttons[j].setBackgroundColor(Color.RED)
                    }
                }
                if (!isCorrect){
                    Game.score -= 2
                }
                binding.tvScore.text = Game.score.toString()
            }
        }
    }

    fun initView(view: View){
        Game.nextLevel()
           
        binding.tvRandom1.text = Game.a.toString()
        binding.tvRandom2.text = Game.b.toString()
        Game.randomOption()
        setValueToButtons()
        for (i in 0..3){
            buttons[i].setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500))
        }
    }

    fun buttonInit(){
        buttons.add(binding.btnOption1)
        buttons.add(binding.btnOption2)
        buttons.add(binding.btnOption3)
        buttons.add(binding.btnOption4)
    }

    fun setValueToButtons(){
        if (Game.randomIndex == 0){
            buttons[0].text = Game.remainA.toString()
            buttons[1].text = Random.nextInt(1..100).toString()
            buttons[2].text = Random.nextInt(1..100).toString()
            buttons[3].text = Random.nextInt(1..100).toString()
        }
        if (Game.randomIndex == 1){
            buttons[1].text = Game.remainA.toString()
            buttons[0].text = Random.nextInt(1..100).toString()
            buttons[2].text = Random.nextInt(1..100).toString()
            buttons[3].text = Random.nextInt(1..100).toString()
        }
        if (Game.randomIndex == 2){
            buttons[2].text = Game.remainA.toString()
            buttons[1].text = Random.nextInt(1..100).toString()
            buttons[3].text = Random.nextInt(1..100).toString()
            buttons[0].text = Random.nextInt(1..100).toString()
        }
        if (Game.randomIndex == 3){
            buttons[3].text = Game.remainA.toString()
            buttons[1].text = Random.nextInt(1..100).toString()
            buttons[2].text = Random.nextInt(1..100).toString()
            buttons[0].text = Random.nextInt(1..100).toString()
        }
    }

}