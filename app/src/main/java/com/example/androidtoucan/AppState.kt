package com.example.androidtoucan

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController


class AppState(
    val navController: NavController,
    var decks : List<Deck> = emptyList(),
    var currentDeck : Deck = Deck(1, "Getting started"),
    var players : MutableList<String> = mutableListOf()
) {

}

@Composable
fun rememberAppState(
    navController: NavController,
    decks : List<Deck> = emptyList(),
    currentDeck : Deck = Deck(1, "Getting started"),
    players : MutableList<String> = mutableListOf()
) = remember(navController, decks, currentDeck, players) {
    AppState(navController, decks, currentDeck, players)
}
