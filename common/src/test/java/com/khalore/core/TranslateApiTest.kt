package com.khalore.core

import com.khalore.core.api.TranslateApi
import com.khalore.core.di.ApiModule
import com.khalore.core.response.Sentence
import com.khalore.domain.translate.Language
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TranslateApiTest {

    private val mockWebServer = MockWebServer()

    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiModule.TRANSLATE_GOOGLE_DOMAIN)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should translate sentence correctly hello world given 200 response`() {
        val api = retrofit.create(TranslateApi::class.java)

        val source = "hello world!"
        val target = "привіт світ!"

        runBlocking {
            val actual = api.getTranslate(
                source = Language.English.code,
                target = Language.Ukrainian.code,
                word = source
            ).let { response ->
                response.body()?.sentences?.map {
                    it.copy(
                        translation = it.translation.lowercase(),
                        original = it.original.lowercase()
                    )
                } ?: emptyList()
            }

            val expected = listOf(
                Sentence(
                    translation = target,
                    original = source
                )
            )

            assertEquals(expected, actual)
        }
    }

    @Test
    fun `should translate sentence correctly given 200 response`() {
        val api = retrofit.create(TranslateApi::class.java)

        val source = "вовк - це рокерська тварина."
        val target = "un lobo es un animal de roca."

        runBlocking {
            val actual = api.getTranslate(
                source = Language.Ukrainian.code,
                target = Language.Spanish.code,
                word = source
            ).let { response ->
                response.body()?.sentences?.map {
                    it.copy(
                        translation = it.translation.lowercase(),
                        original = it.original.lowercase()
                    )
                } ?: emptyList()
            }

            val expected = listOf(
                Sentence(
                    translation = target,
                    original = source
                )
            )

            assertEquals(expected, actual)
        }
    }

    @Test
    fun `should given 400 response with invalid params`() {
        val api = retrofit.create(TranslateApi::class.java)

        runBlocking {
            val response = api.getTranslate(
                source = Language.English.code,
                target = Language.Ukrainian.code,
                word = "word",
                client = ""
            )

            val responseCode = response.code()
            assertTrue(responseCode in 400..499)
        }
    }
}