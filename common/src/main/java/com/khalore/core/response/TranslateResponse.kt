package com.khalore.core.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TranslationData(
    @Json(name = "") val translations: List<List<List<String>>>?,
    @Json(name = "") val languageCode: String?
)

//@JsonClass(generateAdapter = true)
//data class TranslationItem(
//    val sourceText: String?,
//    val translatedText: String?,
//    val detectedSourceLanguage: String?,
//    val model: String?,
//    val confidence: Int?
//)