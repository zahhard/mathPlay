package com.example.mathplay

import kotlin.random.Random
import kotlin.random.nextInt

object Game {
    var a = 0
    var b = 0
    var score = 0
    var level = 0
    var remainA = 0
    var randomIndex = 0
    var startRangA = 0
    var endRangA = 100
    var startRangB = 0
    var endRangB = 10
    var operator = ""


    fun createRandoms(){
        a = Random.nextInt(startRangA..endRangA)
        b = Random.nextInt(startRangB..endRangB)
    }

    fun nextLevel(){
        createRandoms()
        level ++
        calcRemain()
    }

    fun calcRemain (){
        if (a > b){
            remainA = a % b
        }
        else
            remainA = b % a
    }

    fun randomOption(){
        randomIndex = Random.nextInt(0..3)
    }
}