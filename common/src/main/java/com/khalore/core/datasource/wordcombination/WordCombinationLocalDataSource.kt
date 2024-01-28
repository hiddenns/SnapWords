package com.khalore.core.datasource.wordcombination

import com.khalore.core.model.word.WordsCombination
import kotlinx.coroutines.flow.Flow

interface WordCombinationLocalDataSource {

    fun getWordCombinationsFlow(): Flow<List<WordsCombination>>

    fun getWordCombinationByIdFlow(wordsId: Long): Flow<WordsCombination>

    suspend fun insert(words: WordsCombination) : Long

    fun insert(list: List<WordsCombination>)

    fun update(words: WordsCombination)

    fun deleteById(wordsId: Long)

    fun delete(words: WordsCombination)
}