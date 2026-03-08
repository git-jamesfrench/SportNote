package com.jamesfrench.sportnote.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercises",
    foreignKeys = [
        ForeignKey(
            entity = Training::class,
            parentColumns = ["id"],
            childColumns = ["trainingId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("trainingId")]
)
data class Exercise(
    val exercise: Int,
    val primary: Int,
    val amount: Int,
    val series: Int,
    val duration: Long,

    val trainingId: Long,

    @PrimaryKey(autoGenerate = true) val id: Long? = null,
)