package com.shevelev.jpcanilations.features.draggingcard.dto

internal sealed interface CardsListItem {
    val id: Long

    data class CardItem(
        val card: Card,
    ) : CardsListItem {
        override val id: Long
            get() = card.id
    }

    class SeparatorItem(
        override val id: Long,
    ) : CardsListItem
}
