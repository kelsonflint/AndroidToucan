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

@Composable
fun Game(state: AppState) {

    var currPrompt by remember { mutableStateOf(Prompt()) }
    var currIdx by remember { mutableStateOf(0)}

    //shuffle(deck!!.prompts)
    currPrompt = state.currentDeck.prompts[currIdx]

    val modifier = Modifier
        .fillMaxSize()
        .clickable { currIdx++ }
    when (currPrompt.type) {
        //TODO rest of cards
        "normal" -> NormalPrompt(currPrompt, state.players, modifier)
        "bottoms up" -> BottomsUpPrompt(currPrompt, state.players, modifier)
        else -> Text(currPrompt.type)
    }


}


// need to setup queue so it loads before tap
fun formatPrompt(p: Prompt, players: MutableList<String>): String {
    val targetList = players.shuffled().subList(0, p.numTargets)
    return p.prompt.format(*targetList.toTypedArray())
}

@Composable
fun NormalPrompt(p : Prompt, players : MutableList<String>, modifier: Modifier) {
    Box(modifier = modifier.background(Color.Blue))
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = p.type, textAlign = TextAlign.Center, fontSize = 30.sp, color = Color.White)
        Text(text = formatPrompt(p, players), textAlign = TextAlign.Center, fontSize = 18.sp,  color = Color.White)
    }

}


@Composable
fun BottomsUpPrompt(p : Prompt, players : MutableList<String>, modifier: Modifier) {
    Box(modifier = modifier)
    Text(text = p.prompt)
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
