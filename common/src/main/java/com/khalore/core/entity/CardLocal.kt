package com.khalore.core.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.khalore.core.model.card.LanguageLevel


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
    @PrimaryKey(autoGenerate = true) val cardId: Long = 0,
    val wordCombinationId: Long,
    val level: LanguageLevel?,
    val rate: Long,
    val correctResponses: Long = 0,
    val incorrectResponses: Long = 0,
    val lastResponseDate: Long = System.currentTimeMillis()
)