package com.example.androidtoucan


data class Prompt(
    val type: String = "normal",
    val numTargets : Int = 0,
    val prompt : String = "Let's get started!",
    val secondary : String = ""
) {
    init {
        val type = type
        val numTargets = numTargets
        val prompt = prompt
        val secondary = secondary
    }

}
