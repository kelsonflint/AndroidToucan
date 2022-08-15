package com.example.androidtoucan

data class Deck(
    val id: Int,
    val name: String,
    val description : String = "description",
    val prompts : List<Prompt> = emptyList(),
    var locked : Boolean = false
) {
    companion object
}