package com.jamesfrench.sportnote.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jamesfrench.sportnote.data.Exercise
import com.jamesfrench.sportnote.data.Training
import com.jamesfrench.sportnote.data.ExerciseObject

@Database(
    entities = [
        Training::class,
        Exercise::class,
        ExerciseObject::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MainDatabase: RoomDatabase() {
    abstract fun trainingDao(): TrainingDao
}