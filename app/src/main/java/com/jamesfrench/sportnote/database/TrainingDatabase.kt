package com.jamesfrench.sportnote.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        Training::class,
        Exercise::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TrainingDatabase: RoomDatabase() {
    abstract fun trainingDao(): TrainingDao
}