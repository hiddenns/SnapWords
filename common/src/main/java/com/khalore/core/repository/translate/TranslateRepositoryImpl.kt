package com.khalore.core.repository.translate

import com.khalore.core.datasource.remote.translate.TranslateRemoteDataSource
import com.khalore.core.response.TranslationResponse
import com.khalore.domain.translate.Language
import javax.inject.Inject

class TranslateRepositoryImpl @Inject constructor(private val translateRemoteDataSource: TranslateRemoteDataSource) :
    TranslateRepository {

    override suspend fun getTranslate(
        source: Language,
        target: Language,
        word: String,
    ): Result<TranslationResponse> = runCatching {
        return translateRemoteDataSource.getTranslate(source.code, target.code, word)
    }.getOrElse {
        Result.failure(it)
    }

}