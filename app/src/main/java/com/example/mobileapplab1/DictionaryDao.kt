package com.example.mobileapplab1

import androidx.room.*

@Dao
interface DictionaryDao {
    @Transaction
    @Query("SELECT * FROM WordEntity WHERE name = :word")
    fun findByWord(word: String): WordWithMeaningsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWord(word: WordEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeaning(meaning: MeaningEntity)
}