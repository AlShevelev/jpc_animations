package com.shevelev.jpcanilations.features.circlediagram

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun CircleDiagramScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                CircleDiagram(
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f)
                        .align(Alignment.Center)
                        .padding(all = 48.dp),
                    animated = true,
                    arcs = listOf(
                        Arc(
                            from = 0f,
                            to = 1f/3f,
                            color = Color.Red,
                        ),
                        Arc(
                            from = 1f/3f,
                            to = 2f/3f,
                            color = Color.Green,
                        ),
                        Arc(
                            from = 2f/3f,
                            to = 1f,
                            color = Color.Blue,
                        ),
                    )
                )
            }
        }
    }
}