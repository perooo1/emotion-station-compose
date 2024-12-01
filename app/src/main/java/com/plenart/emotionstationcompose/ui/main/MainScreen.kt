package com.plenart.emotionstationcompose.ui.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.plenart.emotionstationcompose.ui.authentication.signIn.SignInScreen

@Composable
fun MainScreen() {
    //Greeting("Petar", modifier = Modifier)
    SignInScreen()
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
