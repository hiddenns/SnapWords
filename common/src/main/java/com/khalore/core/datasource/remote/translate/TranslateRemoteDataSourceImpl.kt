package com.khalore.core.datasource.remote.translate

import com.khalore.core.api.TranslateApi
import com.khalore.core.response.TranslationData
import javax.inject.Inject

class TranslateRemoteDataSourceImpl @Inject constructor(
    private val api: TranslateApi
) : TranslateRemoteDataSource {

    override suspend fun getTranslate(
        source: String,
        target: String,
        word: String
    ): Result<TranslationData> {
        return api.getTranslate(source = source, target = target, word = word)
    }
}