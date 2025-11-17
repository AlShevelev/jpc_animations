package com.shevelev.jpcanilations.features.fuzzylist


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.shevelev.jpcanilations.features.cardrotation.CardFull

@Composable
fun FuzzyListScreen(
    modifier: Modifier = Modifier,
    cards: List<FuzzyListCardData> = FuzzyCardsFactory.cards,
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            LazyColumn(
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .graphicsLayer { alpha = 0.99F }
                    .drawWithContent {
                        val colorStops = listOf(
                            Pair(0f, Color.Transparent),
                            Pair(0.1f, Color.Black),
                            Pair(0.9f, Color.Black),
                            Pair(1f, Color.Transparent),
                        )
                        drawContent()
                        drawRect(
                            brush = Brush.verticalGradient(
                                *colorStops.toTypedArray(),
                                endY = size.height,
                            ),
                            blendMode = BlendMode.DstIn,
                        )
                    }
            ) {
                items(
                    count = cards.size,
                    key = { index -> cards[index].id },
                ) { index ->
                    CardFull(
                        modifier = Modifier.padding(vertical = 8.dp),
                        isRussianSideDefault = true,
                        rusText = cards[index].rusText,
                        engText = cards[index].engText,
                    )
                }
            }
        }
    }
}
