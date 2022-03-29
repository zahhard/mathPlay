package com.example.mathplay

import android.widget.Toast
import androidx.lifecycle.ViewModel
import kotlin.random.Random
import kotlin.random.nextInt

object GameRepository {
    var a = 0
    var b = 0
    var score = 0
    var level = 0
    var result = 0
    var randomIndex = 0
    var startRangA = "0"
    var endRangA = "100"
    var startRangB = "0"
    var endRangB = "10"
    var operator = "%"
    var scoreList = ArrayList<Int>()
}

class ViewModel: ViewModel() {
    fun createRandoms() {
        GameRepository.a = Random.nextInt(GameRepository.startRangA.toInt()..GameRepository.endRangA.toInt())
        GameRepository.b = Random.nextInt(GameRepository.startRangB.toInt()..GameRepository.endRangB.toInt())
    }

    fun nextLevel() {
        createRandoms()
        GameRepository.level++
        calcResult()
    }

    fun calcResult() {
        if (GameRepository.operator =="%") {
            try {
                if (GameRepository.a > GameRepository.b) {
                    GameRepository.result = GameRepository.a % GameRepository.b
                } else {
                    GameRepository.result = GameRepository.b % GameRepository.a
                }
            }
            catch (e: Exception){
            }
        } else if (GameRepository.operator == "*") {
            GameRepository.result = GameRepository.a * GameRepository.b
        } else if (GameRepository.operator == "+") {
            GameRepository.result = GameRepository.a + GameRepository.b
        } else if (GameRepository.operator == "-") {
            if (GameRepository.a > GameRepository.b) {
                GameRepository.result = GameRepository.a - GameRepository.b
            } else {
                GameRepository.result = GameRepository.b - GameRepository.a
            }
        } else if (GameRepository.operator == "/") {
            try {
                if (GameRepository.a > GameRepository.b) {
                    GameRepository.result = GameRepository.a / GameRepository.b
                } else {
                    GameRepository.result = GameRepository.b / GameRepository.a
                }
            }
            catch (e: Exception){

            }
        }
    }

    fun randomOption() {
        GameRepository.randomIndex = Random.nextInt(0..3)
    }

    fun max(): Int? {
        return (GameRepository.scoreList.maxOrNull())
    }
}