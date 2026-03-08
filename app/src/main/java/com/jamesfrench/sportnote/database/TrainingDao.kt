package com.jamesfrench.sportnote.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.jamesfrench.sportnote.data.Training
import com.jamesfrench.sportnote.data.TrainingWithExercises
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainingDao {
    @Insert
    suspend fun insertTraining(training: Training): Long

    @Delete
    suspend fun deleteTraining(training: Training)

    @Transaction
    @Query("SELECT * FROM trainings ORDER BY timestamp DESC")
    fun getTrainings(): Flow<List<TrainingWithExercises>>
}