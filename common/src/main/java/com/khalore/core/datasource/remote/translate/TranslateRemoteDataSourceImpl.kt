package com.khalore.core.datasource.remote.translate

import android.util.Log
import com.khalore.core.api.TranslateApi
import com.khalore.core.response.TranslationResponse
import javax.inject.Inject

class TranslateRemoteDataSourceImpl @Inject constructor(
    private val api: TranslateApi
) : TranslateRemoteDataSource {

    override suspend fun getTranslate(
        source: String,
        target: String,
        word: String
    ): Result<TranslationResponse> {
        return api.getTranslate(source = source, target = target, word = word).let { response ->
            runCatching<TranslationResponse> {
                if (response.isSuccessful) {
                    response.body() ?: throw NullPointerException()
                } else {
                    TranslationResponse(emptyList(), "", emptyMap())
                }
            }
        }.also {
            Log.d("anal", "getTranslate: $it")
        }
    }
}