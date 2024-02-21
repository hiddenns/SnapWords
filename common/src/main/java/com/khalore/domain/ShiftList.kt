package com.khalore.domain

class ShiftList<T>(private var values: List<T>) {

    val data: List<T>
        get() {
            return values
        }

    operator fun get(idx: Int): T = kotlin.runCatching {
        values[idx]
    }.getOrElse {
        values.last()
    }

    fun rotateList() {
        if (values.isEmpty()) return
        val lastElement = values.last()
        values = (listOf(lastElement) + values.subList(0, values.size - 1))
    }

    fun size() = values.size
}

fun <T> List<T>.toShiftList(): ShiftList<T> {
    return ShiftList(this)
}