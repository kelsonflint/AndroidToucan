package com.example.androidtoucan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.Collections.shuffle

@Composable
fun DeckList(state: AppState) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        state.decks.forEach() { deck ->
            DeckCard(state, deck)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DeckCard(state: AppState, deck: Deck) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(50.dp),
        backgroundColor = Color.Gray,
        onClick = {
            state.currentDeck = state.decks.find{ it.name == deck.name}!!
            state.currentDeck.prompts = state.currentDeck.prompts.filter {it.numTargets <= state.players.count()}
            shuffle(state.currentDeck.prompts)
            state.navController.navigate("game/${deck.name}")}
    ) {
        Row() {

        }
        Column() {
            Text(text = deck.name)
            Text(text = deck.description)
        }
    }
}