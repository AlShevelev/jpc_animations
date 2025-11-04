package com.shevelev.jpcanilations.features.draggingcard.viewlogic

import com.shevelev.jpcanilations.features.draggingcard.dto.CardsListItem
import kotlin.math.absoluteValue

internal class CardDragResultCalculator {
    private class InListPosition(
        val list: List<CardsListItem>,
        val index: Int,
    )

    fun calculateUpdates(lists: CardLists, cardId: Long, separatorId: Long): CardLists {
        val dragStart = getInListPosition(lists, cardId)
        val dragEnd = getInListPosition(lists, separatorId)

        return if (dragStart.list == dragEnd.list) {
            processSameLists(lists, dragStart, dragEnd)
        } else {
            processDifferentLists(lists, dragStart, dragEnd)
        }
    }

    private fun getInListPosition(lists: CardLists, id: Long): InListPosition {
        val index = lists.left.indexOfFirst { it.id == id }
        if (index != -1) {
            return InListPosition(
                list = lists.left,
                index = index,
            )
        }

        return InListPosition(
            list = lists.right,
            index = lists.right.indexOfFirst { it.id == id },
        )
    }

    private fun processSameLists(
        lists: CardLists,
        dragStart: InListPosition,
        dragEnd: InListPosition,
    ): CardLists {
        if ((dragStart.index - dragEnd.index).absoluteValue == 1) {
            return lists
        }

        val dragStartListEditor = CardListEditor(dragStart.list)

        val card = dragStartListEditor.removeAndGetCard(dragStart.index) // Card
        dragStartListEditor.remove(dragStart.index) // Separator

        val indexToAdd = if (dragEnd.index < dragStart.index) {
            dragEnd.index
        } else {
            dragEnd.index - 2
        }

        dragStartListEditor.addSeparatorAndCard(indexToAdd, card)

        return if (dragStart.list == lists.left) {
            CardLists(
                left = dragStartListEditor.result,
                right = lists.right,
            )
        } else {
            CardLists(
                left = lists.left,
                right = dragStartListEditor.result,
            )
        }
    }

    private fun processDifferentLists(
        lists: CardLists,
        dragStart: InListPosition,
        dragEnd: InListPosition,
    ): CardLists {
        val dragStartListEditor = CardListEditor(dragStart.list)
        val dragEndListEditor = CardListEditor(dragEnd.list)

        val card = dragStartListEditor.removeAndGetCard(dragStart.index) // Card

        if (dragStartListEditor.size > 1) {
            dragStartListEditor.remove(dragStart.index) // Separator
        }

        dragEndListEditor.addSeparatorAndCard(dragEnd.index, card)

        val resultSourceList = if (dragStart.list == lists.left) {
            dragStartListEditor.result
        } else {
            dragEndListEditor.result
        }

        val resultGroupsList = if (dragStart.list == lists.right) {
            dragStartListEditor.result
        } else {
            dragEndListEditor.result
        }

        return CardLists(
            left = resultSourceList,
            right = resultGroupsList,
        )
    }
}
