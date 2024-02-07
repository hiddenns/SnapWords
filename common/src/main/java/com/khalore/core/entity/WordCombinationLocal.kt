package com.khalore.core.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WordCombinationLocal(
    @PrimaryKey(autoGenerate = true)
    val wordCombinationId: Long = 0,
    val word: String,
    val otherWords: String,
    val description: String? = null,
    val otherDescription: String? = null
)
