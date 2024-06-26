package com.khalore.core.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.khalore.core.entity.WordCombinationLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface WordCombinationDao {
    @Query("SELECT * FROM WordCombinationLocal")
    fun getWordCombinationsFlow(): Flow<List<WordCombinationLocal>>

    @Query("SELECT * FROM WordCombinationLocal WHERE WordCombinationLocal.wordCombinationId = :wordCombinationId")
    fun getWordCombinationByIdFlow(wordCombinationId: Long): Flow<WordCombinationLocal>

    @Query("SELECT * FROM WordCombinationLocal WHERE WordCombinationLocal.wordCombinationId = :wordCombinationId")
    fun getWordCombinationById(wordCombinationId: Long): WordCombinationLocal


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(words: WordCombinationLocal) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(wordList: List<WordCombinationLocal>)

    @Update
    fun update(words: WordCombinationLocal)

    @Query("DELETE FROM WordCombinationLocal WHERE wordCombinationId = :wordCombinationId")
    suspend fun deleteById(wordCombinationId: Long)

    @Delete
    suspend fun delete(wordCombinationId: WordCombinationLocal)
}