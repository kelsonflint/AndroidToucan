package com.example.androidtoucan

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MainMenuScreen(
    state: AppState
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Toucan", fontSize = 30.sp)
        PlayButton(state.navController)

        var name by remember { mutableStateOf("")}
        Column {
            Row() {
                state.players.forEach { Text(it, modifier = Modifier.padding(5.dp)) }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Add player")})
                Button(onClick = {
                    state.players.add(name)
                    name = ""
                }, Modifier.clickable { name.isNotBlank() }) {
                    Text("Add")
                }
            }
        }



    }
}


@Composable
fun PlayButton(
    navController: NavController
) {
    Button(
        modifier = Modifier.fillMaxWidth(0.9f),
        onClick = { navController.navigate("deckList")}
    ) {
        Text("Let's Play")
    }
}