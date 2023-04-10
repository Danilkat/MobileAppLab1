package com.example.mobileapplab1

import androidx.room.*

@Dao
interface DictionaryDao {
    @Transaction
    @Query("SELECT * FROM WordEntity WHERE name = :word")
    fun findByWord(word: String): WordWithMeaningsEntity?

    @Query("SELECT meaning.name AS name, meaning.meaning AS v_meaning FROM MeaningEntity AS meaning " +
            "JOIN WordEntity AS word " +
            "ON meaning.name = word.name " +
            "GROUP BY meaning.name " +
            "ORDER BY word.learning_speed ASC")
    fun getUniqueMeanings(): List<TestObject>

    @Query("SELECT COUNT(*) FROM WordEntity")
    fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWord(word: WordEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeaning(meaning: MeaningEntity)

    @Query("UPDATE WordEntity SET learning_speed = learning_speed + 1 WHERE name = :word")
    fun plusLearningSpeed(word: String)

    @Query("UPDATE WordEntity SET learning_speed = learning_speed - 1 WHERE name = :word")
    fun minusLearningSpeed(word: String)
}