package com.shevelev.jpcanilations.features.draggingcard.viewlogic

import com.shevelev.jpcanilations.features.draggingcard.dto.Card
import com.shevelev.jpcanilations.features.draggingcard.dto.CardsListItem
import com.shevelev.jpcanilations.features.draggingcard.viewlogic.idgenerator.IdGenerator

internal class CardsLogicFacade {
    private val dragResultCalculator by lazy { CardDragResultCalculator() }

    fun getInitialLists() = CardLists(
        left = mutableListOf(
                Card(
                    id = IdGenerator.newId(),
                    text = "Fire roared through the bifurcated city of Ankh-Morpork."
                ),
                Card(
                    id = IdGenerator.newId(),
                    text = "Where it licked the Wizards' Quarter it burned blue and green and was" +
                            " even laced with strange sparks of the eighth color, octarine;"
                ),
                Card(
                    id = IdGenerator.newId(),
                    text = "where its outriders found their way into the vats and oil stores all" +
                            " along Merchant Street it progressed in a series of blazing fountains" +
                            " and explosions;"
                ),
                Card(
                    id = IdGenerator.newId(),
                    text = "where it touched bundles of rare and dry herbs in the storerooms of " +
                            "the drugmasters it made men go mad and talk to God."
                ),
                Card(
                    id = IdGenerator.newId(),
                    text = "By now the whole of downtown Morpork was alight, and the richer and " +
                            "worthier citizens of Ankh on the far bank were bravely responding to " +
                            "the situation by feverishly demolishing the bridges."
                ),
                Card(
                    id = IdGenerator.newId(),
                    text = "But already the ships in the Morpork docks-laden with grain, cotton " +
                            "and timber, and coated with tar-were blazing merrily and, their " +
                            "moorings burnt to ashes, were breasting the river Ankh on the ebb " +
                            "tide, igniting riverside palaces and bowers as they drifted like " +
                            "drowning fireflies toward the sea."
                ),
                Card(
                    id = IdGenerator.newId(),
                    text = "In any case, sparks were riding the breeze and touching down far " +
                            "across the river in hidden gardens and remote rickyards."
                ),
                Card(
                    id = IdGenerator.newId(),
                    text = "The smoke from the merry burning rose miles high, in a wind-sculpted " +
                            "black column that could be seen across the whole of the Discworld."
                ),
                Card(
                    id = IdGenerator.newId(),
                    text = "It was certainly impressive from the cool, dark hilltop a few leagues " +
                            "away, where two figures were watching with considerable interest."
                ),
                Card(
                    id = IdGenerator.newId(),
                    text = "The taller of the pair was chewing on a chicken leg and Mugu"
                ),
        ).let { toViewList(it) },
        right = toViewList(mutableListOf<Card>())
    )

    fun processDropCard(lists: CardLists, cardId: Long, separatorId: Long): CardLists =
        dragResultCalculator.calculateUpdates(lists, cardId, separatorId)

    private fun toViewList(cards: List<Card>): List<CardsListItem> {
        val editor = CardListEditor()

        editor.addSeparator()

        cards.forEach {
            editor.addCard(it)
            editor.addSeparator()
        }

        return editor.result
    }
}