package com.example.mobileapplab1

import com.squareup.moshi.Json

data class WordInformation(
    @Json(name="word")
    val word: String,

    @Json(name = "phonetics")
    val phonetics: List<Phonetics>,

    val meanings: List<Meaning>)

data class Phonetics(
    @Json(name = "text")
    val transcription: String? = "",

    @Json(name = "audio")
    val audio: String
)

data class Meaning(
    @Json(name = "partOfSpeech")
    val partOfSpeech: String,

    @Json(name = "definitions")
    val definitions: List<Definition>
)

data class Definition(
    @Json(name="definition")
    var definition: String,

    @Json(name="example")
    var example: String? = ""
)
