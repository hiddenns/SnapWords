package com.khalore.core.datasource.local.wordcombination

import com.khalore.core.dao.WordCombinationDao
import com.khalore.core.mappers.toDomain
import com.khalore.core.mappers.toLocal
import com.khalore.core.mappers.toLocalWithId
import com.khalore.core.model.word.WordsCombination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WordCombinationLocalDataSourceImpl @Inject constructor(
    private val wordCombinationDao: WordCombinationDao
) : WordCombinationLocalDataSource {
    override fun getWordCombinationsFlow(): Flow<List<WordsCombination>> {
        return wordCombinationDao.getWordCombinationsFlow().map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

    override fun getWordCombinationByIdFlow(wordsId: Long): Flow<WordsCombination> {
        return wordCombinationDao.getWordCombinationByIdFlow(wordsId).map { word ->
            word.toDomain()
        }
    }

    override suspend fun insert(words: WordsCombination): Long {
        return wordCombinationDao.insert(words.toLocal())
    }

    override fun insert(list: List<WordsCombination>) {
        wordCombinationDao.insert(list.map {
            it.toLocal()
        })
    }

    override fun update(words: WordsCombination) {
        wordCombinationDao.update(words.toLocalWithId())
    }

    override suspend fun deleteById(wordsId: Long) {
        wordCombinationDao.deleteById(wordsId)
    }

    override suspend fun delete(words: WordsCombination) {
        wordCombinationDao.delete(words.toLocal())
    }


}