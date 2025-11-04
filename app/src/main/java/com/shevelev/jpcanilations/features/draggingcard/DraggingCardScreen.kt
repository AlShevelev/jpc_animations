package com.shevelev.jpcanilations.features.draggingcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shevelev.jpcanilations.features.draggingcard.viewlogic.CardsLogicFacade

@Composable
internal fun DraggingCardScreen(
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
                val cardsLogic = remember { CardsLogicFacade() }
                var cards by remember { mutableStateOf(cardsLogic.getInitialLists()) }

                LongPressDraggable(
                    modifier = modifier,
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        CardsBriefList(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(start = 16.dp)
                                .weight(1f),
                            cards = cards.left,
                            onDrop = { card, separatorId ->
                                cards = cardsLogic.processDropCard(cards, card.id, separatorId)
                            },
                        )

                        Spacer(
                            modifier = Modifier.width(16.dp),
                        )

                        CardsBriefList(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(end = 16.dp)
                                .weight(1f),
                            cards = cards.right,
                            onDrop = { card, separatorId ->
                                cards = cardsLogic.processDropCard(cards, card.id, separatorId)
                            },
                        )
                    }
                }
            }
        }
    }
}