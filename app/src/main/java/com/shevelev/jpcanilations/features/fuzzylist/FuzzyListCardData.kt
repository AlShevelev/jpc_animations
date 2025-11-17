package com.shevelev.jpcanilations.features.fuzzylist

import com.shevelev.jpcanilations.features.draggingcard.viewlogic.idgenerator.IdGenerator

data class FuzzyListCardData(
    val id: Long = IdGenerator.newId(),
    val rusText: String,
    val engText: String,
)