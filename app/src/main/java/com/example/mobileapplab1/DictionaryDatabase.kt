package com.example.mobileapplab1

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [
    WordEntity::class,
    MeaningEntity::class
],
version = 4)
abstract class DictionaryDatabase: RoomDatabase() {

    abstract fun dictionaryDao(): DictionaryDao

}