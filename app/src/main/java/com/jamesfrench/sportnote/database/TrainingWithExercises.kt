package com.jamesfrench.sportnote.database

import androidx.room.Embedded
import androidx.room.Relation

data class TrainingWithExercises(
    @Embedded val training: Training,

    @Relation(
        parentColumn = "id",
        entityColumn = "trainingId"
    )
    val exercises: List<Exercise>
)