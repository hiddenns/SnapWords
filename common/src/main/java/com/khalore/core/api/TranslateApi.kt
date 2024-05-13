package com.khalore.core.api

import com.khalore.core.response.TranslationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TranslateApi {

    @GET(TRANSLATE_WORD)
    suspend fun getTranslate(
        @Query("sl") source: String,
        @Query("tl") target: String,
        @Query("q") word: String,
        @Query("dj") dj: String = "1",
        @Query("dt") dt: String = "t",
        @Query("client") client: String = "gtx"
    ): Response<TranslationResponse>


    companion object {
        private const val TRANSLATE_WORD = "translate_a/single"
    }

}