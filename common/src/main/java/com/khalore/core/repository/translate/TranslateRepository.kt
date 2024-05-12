package com.khalore.core.repository.translate

import com.khalore.core.response.TranslationData

interface TranslateRepository {

    suspend  fun getTranslate(
        source: String,
        target: String,
        word: String,
    ): Result<TranslationData?>

}