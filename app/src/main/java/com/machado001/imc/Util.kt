package com.machado001.imc

import kotlin.math.pow

fun calculateIMC(weight: Float?, height: Float?) = height?.let {
    // weight / (height * height), but with null safety
    weight?.div(it.pow(2))
}

fun checkBoundaries(result: Float?): String {
    return when (result!!) {
        in Double.MIN_VALUE..18.4 -> "Underweight"
        in 18.5..24.9 -> "Normal"
        in 25.0..29.9 -> "Overweight"
        in 30.0..34.9 -> "Obesity Class I"
        in 35.0..39.9 -> "Obesity Class II"
        in 40.0..Double.MAX_VALUE -> "Obesity Class III"
        else -> "Unknown "
    }
}
