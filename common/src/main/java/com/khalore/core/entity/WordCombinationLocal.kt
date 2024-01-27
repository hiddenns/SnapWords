package com.khalore.core.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Locale

@Entity
data class WordCombinationLocal(
    @PrimaryKey(autoGenerate = true)
    val wordCombinationId: Long,
    val word: String,
    val translate: String,
    val description: String? = null,
    val translateDescription: String? = null,
    val language: String = Locale.ENGLISH.language
)
