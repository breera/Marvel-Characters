package com.breera.marvelcharactersapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.breera.character_feature.presentation.home.HomeScreenRoot
import com.breera.theme.theme.MarvelCharactersAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarvelCharactersAppTheme {
                HomeScreenRoot()
            }
        }
    }
}
