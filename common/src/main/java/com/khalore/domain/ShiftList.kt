package com.khalore.domain

class ShiftList<T>(private var values: List<T>) {

    operator fun get(idx: Int): T = values[idx]

    fun rotateList() {
        if (values.isEmpty()) return
        val lastElement = values.last()
        values = (listOf(lastElement) + values.subList(0, values.size - 1))
    }
}

fun <T> List<T>.toShiftList(): ShiftList<T> {
    return ShiftList(this)
}