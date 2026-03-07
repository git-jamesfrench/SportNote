package com.jamesfrench.sportnote.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "trainings")
data class Training(
    val title: String,
    val note: String,
    val timestamp: Long,

    @PrimaryKey(autoGenerate = true) val id: Long? = null,
)