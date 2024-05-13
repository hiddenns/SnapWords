package com.khalore.core.response

import com.google.gson.annotations.SerializedName
data class Sentence(
    @SerializedName("trans") val translation: String,
    @SerializedName( "orig") val original: String,
    @SerializedName( "backend") val backend: Int
)

data class TranslationResponse(
    @SerializedName( "sentences") val sentences: List<Sentence>,
    @SerializedName( "src")  val src: String,
    @SerializedName( "spell") val spell: Map<String, Any>
)