package com.example.mobileapplab1

import androidx.room.*

@Entity
data class WordEntity(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    @ColumnInfo(name = "transcription") val transcription: String?,
    @ColumnInfo(name = "part_of_speech") val partOfSpeech: String?,
    @ColumnInfo(name = "audio") val audio: String?,
    @ColumnInfo(name = "learning_speed") val learningSpeed: Int = 0
)

@Entity(primaryKeys = ["name", "meaning"])
data class MeaningEntity(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "meaning") val meaning: String,
    @ColumnInfo(name = "example") val example: String?,
)

@Entity
data class WordWithMeaningsEntity(
    @Embedded val word: WordEntity,
    @Relation(
        parentColumn = "name",
        entityColumn = "name"
    )
    val meanings: List<MeaningEntity>
)

data class TestObject(
    val name: String = "",
    val v_meaning: String = ""
)
