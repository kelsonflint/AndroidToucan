package com.example.androidtoucan

import android.content.Context
import android.content.res.AssetManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.io.IOException
import java.util.Collections.copy
import java.util.Collections.shuffle

/* 
       Prompt in middle
       Menu Top Right
       Back Button top left
       Background color dependent on card type
       Tap to skip to next prompt
       
*/

val colorMap = mapOf (
    "normal" to Color.Blue,
    "game" to Color.Green,
    "virus" to Color.Yellow,
    "bottoms_up" to Color.Red,
    "punishment" to Color.Black,
    "spicy" to Color.Magenta
)

@Composable
fun Game(state: AppState) {

    var currPrompt by remember { mutableStateOf(Prompt()) }
    var currIdx by remember { mutableStateOf(0)}

    currPrompt = state.currentDeck.prompts[currIdx]

    val modifier = Modifier
        .fillMaxSize()
        .clickable { currIdx++ }
    PromptScreen(currPrompt, state.players, modifier)
}


// TODO need to setup queue so it loads before tap
fun formatPrompt(p: Prompt, players: MutableList<String>): String {
    return if (players.isNotEmpty()) {
        val targetList = players.shuffled().subList(0, p.numTargets)
        p.prompt.format(*targetList.toTypedArray())
    } else {
        p.prompt
    }
}

@Composable
fun PromptScreen(p : Prompt, players : MutableList<String>, modifier: Modifier) {
    Box(modifier = modifier.background(colorMap.getOrDefault(p.type, Color.Blue)), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            if (p.type != "normal") Text(text = p.type, textAlign = TextAlign.Center, fontSize = 30.sp, color = Color.White)
            Text(text = formatPrompt(p, players), textAlign = TextAlign.Center, fontSize = 18.sp,  color = Color.White)
        }
    }
    //TODO add secondary cards

}

fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}
