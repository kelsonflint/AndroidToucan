package com.example.androidtoucan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun MainMenuScreen(
    state: AppState
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.toucan_logo), "Logo",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        )
        var name by remember { mutableStateOf("")}
        val focusManager = LocalFocusManager.current
        Column( horizontalAlignment = Alignment.CenterHorizontally) {
            FlowRow {
                if (state.players.isNotEmpty()) {
                    state.players.forEach { Chip(it) }
                } else {
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Add player")},
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus()})
                )
                Button(onClick = {
                    state.players.add(name)
                    name = ""
                },
                    Modifier
                        .padding(5.dp)
                        .clickable { name.isNotBlank() }) {
                    Text("Add")
                }
            }
            PlayButton(state.navController)
        }
    }
}

@Composable
fun Chip(text: String) {
    Row(
        horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(shape = RoundedCornerShape(12))
            .padding(5.dp)
            .height(30.dp)
            .background(Color.Blue, shape = RoundedCornerShape(12))
    ) {
        Text(text, color = Color.White, modifier = Modifier.padding(5.dp))
    }
}

@Composable
fun PlayButton(
    navController: NavController
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp, 20.dp),
        onClick = { navController.navigate("deckList")}
    ) {
        Text("Let's Play")
    }
}