package com.jamesfrench.sportnote.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow



@Dao
interface TrainingDao {
    @Insert
    suspend fun insertTraining(training: Training)

    @Delete
    suspend fun deleteTraining(training: Training)

    @Transaction
    @Query("SELECT * FROM trainings ORDER BY timestamp DESC")
    fun getTrainings(): Flow<List<TrainingWithExercises>>
}