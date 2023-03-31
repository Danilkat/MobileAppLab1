package com.example.mobileapplab1

import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {
    @GET("{word}")
    suspend fun getWordMeaning(@Path("word") word: String): List<WordInformation>
}