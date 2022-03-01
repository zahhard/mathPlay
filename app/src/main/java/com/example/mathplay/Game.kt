package com.example.mathplay

import kotlin.random.Random
import kotlin.random.nextInt

object Game {
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
    var operator = ""


    fun createRandoms(){
        a = Random.nextInt(startRangA.toInt()..endRangA.toInt())
        b = Random.nextInt(startRangB.toInt()..endRangB.toInt())
    }

    fun nextLevel(){
        createRandoms()
        level ++

        calcResult()
    }

    fun calcResult (){
        if (operator == "%") {
            if (a > b) {
                result = a % b
            } else
                result = b % a
        }

        if (operator == "*"){
            result = a * b
        }
        if (operator == "+"){
            result = a + b
        }
        if (operator == "-"){
            if (a > b) {
                result = a - b
            } else
                result = b - a
        }
        if (operator == "/"){
            if (a > b) {
                result = a / b
            } else
                result = b / a
        }
    }

    fun randomOption(){
        randomIndex = Random.nextInt(0..3)
    }
}