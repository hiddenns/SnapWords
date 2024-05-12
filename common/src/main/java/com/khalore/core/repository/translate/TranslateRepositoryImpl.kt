package com.khalore.core.repository.translate

import com.khalore.core.datasource.remote.translate.TranslateRemoteDataSource
import com.khalore.core.response.TranslationData
import javax.inject.Inject

class TranslateRepositoryImpl @Inject constructor(private val translateRemoteDataSource: TranslateRemoteDataSource) :
    TranslateRepository {

    override suspend  fun getTranslate(
        source: String,
        target: String,
        word: String,
    ): Result<TranslationData> {
        return translateRemoteDataSource.getTranslate(source, target, word)
    }

}