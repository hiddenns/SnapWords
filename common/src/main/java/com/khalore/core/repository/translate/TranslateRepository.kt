package com.khalore.core.repository.translate

import com.khalore.core.response.TranslationResponse
import com.khalore.domain.translate.Language

interface TranslateRepository {

    suspend  fun getTranslate(
        source: Language,
        target: Language,
        word: String,
    ): Result<TranslationResponse>

}