package com.shevelev.jpcanilations.mainmenu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
            Column(
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                MenuButton(
                    text = "Glitch Button",
                    onClick = { backstack.add(Route.GlitchButton) }
                )
                MenuButton(
                    text = "CardRotation",
                    onClick = { backstack.add(Route.CardRotation) }
                )
            }
        }
    }
}

@Composable
private fun ColumnScope.MenuButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier.align(Alignment.CenterHorizontally)
    ) {
        Text(text)
    }
}