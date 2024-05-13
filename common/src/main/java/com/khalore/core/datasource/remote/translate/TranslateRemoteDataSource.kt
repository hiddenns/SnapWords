package com.khalore.core.datasource.remote.translate

import com.khalore.core.response.TranslationResponse

interface TranslateRemoteDataSource {

    suspend  fun getTranslate(
        source: String,
        target: String,
        word: String,
    ): Result<TranslationResponse>

}