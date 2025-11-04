package com.shevelev.jpcanilations.features.draggingcard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shevelev.jpcanilations.features.draggingcard.dto.CardsListItem

@Composable
internal fun CardBrief(
    item: CardsListItem.CardItem,
    modifier: Modifier = Modifier,
) {
    DragTarget(
        dataToDrop = item.card,
    ) { contentModifier ->
        Card(
            modifier = contentModifier
                .height(CardListDimens.cardHeight),
            shape = RoundedCornerShape(7.dp),
            border = BorderStroke(
                width = 1.dp,
                brush = SolidColor(Color.LightGray),
            ),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(all = 8.dp),
            ) {
                Text(
                    text = item.card.text,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}
