package com.shevelev.jpcanilations.features.draggingcard.viewlogic

import com.shevelev.jpcanilations.features.draggingcard.dto.CardsListItem

internal data class CardLists(
    val left: List<CardsListItem>,
    val right: List<CardsListItem>,
)