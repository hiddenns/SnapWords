package com.khalore.core.api

import com.khalore.core.response.TranslationData
import retrofit2.http.GET
import retrofit2.http.Query

interface TranslateApi {

    @GET(TRANSLATE_WORD)
    suspend  fun getTranslate(
        @Query("sl") source: String,
        @Query("tl") target: String,
        @Query("q") word: String,
        @Query("dt") dt: String = "t",
        @Query("client") client: String = "gtx"
    ): Result<TranslationData>


    companion object {
        private const val TRANSLATE_WORD = "translate_a/single"
    }

}