package com.example.mathplay

import kotlin.random.Random
import kotlin.random.nextInt

object Game {
    var a = 0
    var b = 0
    var score = 0
    var level = 1
    var remainA = 0

    fun createRandoms(){
        a = Random.nextInt(1..100)
        b = Random.nextInt(1..10)
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
}