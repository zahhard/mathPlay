package com.example.mathplay

import kotlin.random.Random
import kotlin.random.nextInt

object Game {
    var a = 1
    var b = 1
    var score = 0
    var level = 0
    var result = 0
    var randomIndex = 0
    var startRangA = "1"
    var endRangA = "100"
    var startRangB = "1"
    var endRangB = "10"
    var operator = "%"
    var firstOption = 1
    var secondOption = 2
    var thirdOption = 3
    var scoreList = ArrayList<Int>()


    fun createRandoms() {
        a = Random.nextInt(startRangA.toInt()..endRangA.toInt())
        b = Random.nextInt(startRangB.toInt()..endRangB.toInt())
    }

    fun nextLevel() {
        createRandoms()
        createValueForOptions()
        level++
        calcResult()
    }

    fun createValueForOptions(){
        var endRange = endRangA.toInt()
        if (operator == "*" || operator == "+")
            endRange = endRangA.toInt() * endRangB.toInt()
        if (operator == "-" || operator == "/" || operator == "%")
            endRange = endRangA.toInt() - endRangB.toInt()

        firstOption = Random.nextInt(1 .. endRange.toInt())
        secondOption = Random.nextInt(1 .. endRange.toInt())
        thirdOption = Random.nextInt(1 .. endRange.toInt())
    }

    fun calcResult() {
        if (operator =="%") {
            if (a > b) {
                result = a % b
            } else {
                result = b % a
            }
        } else if (operator == "*") {
            result = a * b
        } else if (operator == "+") {
            result = a + b
        } else if (operator == "-") {
            if (a > b) {
                result = a - b
            } else {
                result = b - a
            }
        } else if (operator == "/") {
            if (a > b) {
                result = a / b
            } else {
                result = b / a
            }
        }
    }

    fun randomOption() {
        randomIndex = Random.nextInt(0..3)
    }

    fun max(): Int? {
        return (scoreList.maxOrNull())
    }
}