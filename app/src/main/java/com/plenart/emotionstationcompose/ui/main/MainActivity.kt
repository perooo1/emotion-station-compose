package com.plenart.emotionstationcompose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.plenart.emotionstationcompose.ui.theme.EmotionStationComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            EmotionStationComposeTheme {
                MainScreen()
            }
        }
    }
}



