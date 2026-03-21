package com.jamesfrench.sportnote.database

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Exercise(
    @Id
    var id: Long = 0,
    var exerciseType: Long = 0,
    var weight: Int = 0
)