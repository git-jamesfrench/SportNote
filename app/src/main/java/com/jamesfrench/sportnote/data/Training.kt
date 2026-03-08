package com.jamesfrench.sportnote.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trainings")
data class Training(
    val title: String,
    val note: String,
    val timestamp: Long,

    @PrimaryKey(autoGenerate = true) val id: Long? = null,
)