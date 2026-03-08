package com.jamesfrench.sportnote.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercises_objects"
)
data class ExerciseObject(
    val name: String,
    val icon: Int,
    val type: Int, // 0: Musculation (kg), 1: Difficulty, 2: Speed (km/h)
    @PrimaryKey(autoGenerate = true) val id: Int,
)
