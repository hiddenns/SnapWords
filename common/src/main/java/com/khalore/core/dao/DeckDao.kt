package com.khalore.core.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.khalore.core.entity.deck.DeckWithCardLocal
import com.khalore.core.entity.deck.DeckWithCardsDTO

@Dao
interface DeckDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(join: DeckWithCardLocal)

    @Transaction
    @Query("SELECT * FROM DeckLocal")
    fun getDeck(): List<DeckWithCardsDTO>

}