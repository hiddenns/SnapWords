package com.khalore.core.datasource.local.wordcombination

import com.khalore.core.model.word.WordsCombination
import kotlinx.coroutines.flow.Flow

interface WordCombinationLocalDataSource {

    fun getWordCombinationsFlow(): Flow<List<WordsCombination>>

    fun getWordCombinationByIdFlow(wordsId: Long): Flow<WordsCombination>

    suspend fun insert(words: WordsCombination) : Long

    fun insert(list: List<WordsCombination>)

    fun update(words: WordsCombination)

    suspend fun deleteById(wordsId: Long)

    suspend fun delete(words: WordsCombination)
}