package com.khalore.core.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [ForeignKey(
        entity = WordCombinationLocal::class,
        parentColumns = ["wordCombinationId"],
        childColumns = ["wordCombinationId"],
        onDelete = CASCADE,
        onUpdate = CASCADE
    )]
)
data class CardLocal(
    @PrimaryKey(autoGenerate = true) val cardId: Long,
    val wordCombinationId: Long,
    val rate: Long = 0
)