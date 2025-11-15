package com.shevelev.jpcanilations.mainmenu

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.sp
import com.shevelev.jpcanilations.ui.navigation.Route

@Composable
internal fun MainMenu(
    modifier: Modifier = Modifier,
    backstack: MutableList<Route>,
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                MenuButton(
                    text = "Glitch Button",
                    onClick = { backstack.add(Route.GlitchButton) }
                )
                MenuButton(
                    text = "Card's rotation",
                    onClick = { backstack.add(Route.CardRotation) }
                )
                MenuButton(
                    text = "Dragging a card",
                    onClick = { backstack.add(Route.DraggingCard) }
                )
                MenuButton(
                    text = "Circle diagram",
                    onClick = { backstack.add(Route.CircleDiagram) }
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
        Text(
            text,
            fontSize = 24.sp,
        )
    }
}