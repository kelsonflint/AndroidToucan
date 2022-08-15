package com.example.androidtoucan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidtoucan.ui.theme.AndroidToucanTheme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidToucanTheme {
                mainApp(getDecks())
            }
        }
    }

    fun getDecks(): List<Deck> {
        val gson = Gson()
        val str = getJsonDataFromAsset(this.applicationContext, "prompts.json")
        println("GET DECKS")
        return gson.fromJson<List<Deck>?>(str, object : TypeToken<List<Deck>>() {}.type).shuffled()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

@Composable
fun mainApp(decks: List<Deck>) {
    AndroidToucanTheme {
        val navController = rememberNavController()
        val state = rememberAppState(navController, decks)

        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            NavHost(navController = navController, startDestination = "menu") {
                composable("menu") { MainMenuScreen(state) }
                composable("deckList") { DeckList(state) }
                composable("game/{deckName}") { Game(state) }
            }
        }
    }
}






@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidToucanTheme {
    }
}