package com.shevelev.jpcanilations.features.draggingcard.viewlogic.idgenerator

/**
 * A pure Kotlin implementation of the Murmur 2 hashing algorithm as presented
 * https://sites.google.com/site/murmurhash
 */
internal object Murmur2 {
    private const val MASK: Long = 0xFFFFFFFFL

    fun hash(data: ByteArray, seed: Long): Long {
        val length = data.size

        val m = -0x395b586ca42e166bL
        val r = 47

        var h = seed and MASK xor length * m

        val length8 = length shr 3

        for (i in 0 until length8) {
            val i8 = i shl 3

            var k = (
                (data[i8].toLong() and 0xff) + (data[i8 + 1].toLong() and 0xff shl 8) +
                    (data[i8 + 2].toLong() and 0xff shl 16) + (data[i8 + 3].toLong() and 0xff shl 24) +
                    (data[i8 + 4].toLong() and 0xff shl 32) + (data[i8 + 5].toLong() and 0xff shl 40) +
                    (data[i8 + 6].toLong() and 0xff shl 48) + (data[i8 + 7].toLong() and 0xff shl 56)
                )

            k *= m
            k = k xor k.ushr(r)
            k *= m

            h = h xor k
            h *= m
        }

        when (length and 7) {
            7 -> {
                h = h xor ((data[(length and 7.inv()) + 6].toLong() and 0xff) shl 48)
                h = h xor ((data[(length and 7.inv()) + 5].toLong() and 0xff) shl 40)
                h = h xor ((data[(length and 7.inv()) + 4].toLong() and 0xff) shl 32)
                h = h xor ((data[(length and 7.inv()) + 3].toLong() and 0xff) shl 24)
                h = h xor ((data[(length and 7.inv()) + 2].toLong() and 0xff) shl 16)
                h = h xor ((data[(length and 7.inv()) + 1].toLong() and 0xff) shl 8)
                h = h xor (data[length and 7.inv()].toLong() and 0xff)
                h *= m
            }

            6 -> {
                h = h xor ((data[(length and 7.inv()) + 5].toLong() and 0xff) shl 40)
                h = h xor ((data[(length and 7.inv()) + 4].toLong() and 0xff) shl 32)
                h = h xor ((data[(length and 7.inv()) + 3].toLong() and 0xff) shl 24)
                h = h xor ((data[(length and 7.inv()) + 2].toLong() and 0xff) shl 16)
                h = h xor ((data[(length and 7.inv()) + 1].toLong() and 0xff) shl 8)
                h = h xor (data[length and 7.inv()].toLong() and 0xff)
                h *= m
            }

            5 -> {
                h = h xor ((data[(length and 7.inv()) + 4].toLong() and 0xff) shl 32)
                h = h xor ((data[(length and 7.inv()) + 3].toLong() and 0xff) shl 24)
                h = h xor ((data[(length and 7.inv()) + 2].toLong() and 0xff) shl 16)
                h = h xor ((data[(length and 7.inv()) + 1].toLong() and 0xff) shl 8)
                h = h xor (data[length and 7.inv()].toLong() and 0xff)
                h *= m
            }

            4 -> {
                h = h xor ((data[(length and 7.inv()) + 3].toLong() and 0xff) shl 24)
                h = h xor ((data[(length and 7.inv()) + 2].toLong() and 0xff) shl 16)
                h = h xor ((data[(length and 7.inv()) + 1].toLong() and 0xff) shl 8)
                h = h xor (data[length and 7.inv()].toLong() and 0xff)
                h *= m
            }

            3 -> {
                h = h xor ((data[(length and 7.inv()) + 2].toLong() and 0xff) shl 16)
                h = h xor ((data[(length and 7.inv()) + 1].toLong() and 0xff) shl 8)
                h = h xor (data[length and 7.inv()].toLong() and 0xff)
                h *= m
            }

            2 -> {
                h = h xor ((data[(length and 7.inv()) + 1].toLong() and 0xff) shl 8)
                h = h xor (data[length and 7.inv()].toLong() and 0xff)
                h *= m
            }

            1 -> {
                h = h xor (data[length and 7.inv()].toLong() and 0xff)
                h *= m
            }
        }

        h = h xor h.ushr(r)
        h *= m
        h = h xor h.ushr(r)

        return h
    }
}
