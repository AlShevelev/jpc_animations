package com.shevelev.jpcanilations.features.draggingcard.viewlogic.idgenerator

import kotlin.random.Random

internal object IdGenerator {
    fun newId(): Long {
        val buffer = Random.nextBytes(32)
        val seed = Random.nextLong()

        return Murmur2.hash(buffer, seed)
    }
}
