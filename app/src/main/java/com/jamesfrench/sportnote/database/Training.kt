package com.jamesfrench.sportnote.database

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

@Entity
data class Training(
    @Id
    var id: Long = 0,
    var name: String = "",
    var createdAt: Long = 0,

) {
    lateinit var exercises: ToMany<Exercise>
}