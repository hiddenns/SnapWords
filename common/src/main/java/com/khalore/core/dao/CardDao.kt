package com.khalore.core.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.khalore.core.entity.CardLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Query("SELECT * FROM CardLocal")
    fun getCardsFlow(): Flow<List<CardLocal>>

    @Query("SELECT * FROM CardLocal WHERE CardLocal.cardId = :cardId")
    fun getCardByIdFlow(cardId: Long): Flow<CardLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(card: CardLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(card: List<CardLocal>)

    @Update
    fun update(card: CardLocal)

    @Query("DELETE FROM CardLocal WHERE cardId = :cardId")
    fun deleteById(cardId: Long)

    @Delete
    fun delete(card: CardLocal)
}