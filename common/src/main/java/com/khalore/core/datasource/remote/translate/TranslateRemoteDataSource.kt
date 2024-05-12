package com.khalore.core.datasource.remote.translate

import com.khalore.core.response.TranslationData

interface TranslateRemoteDataSource {

    suspend  fun getTranslate(
        source: String,
        target: String,
        word: String,
    ): Result<TranslationData>

}