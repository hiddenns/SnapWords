package com.khalore.core.entity.deck

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DeckLocal(
    @PrimaryKey(autoGenerate = true)
    val deckId: Long = 0,
    val name: String,
    val accessId: Int,
    val isOwned: Boolean = false,
)
