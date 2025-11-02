package com.shevelev.jpcanilations.mainmenu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.shevelev.jpcanilations.ui.navigation.Route

@Composable
fun MainMenu(
    modifier: Modifier = Modifier,
    backstack: MutableList<Route>,
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Button(
                    onClick = {
                        backstack.add(Route.GlitchButton)
                    },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text("Glitch Button")
                }
            }
        }
    }
}