package com.jamesfrench.sportnote.database

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Training(
    @Id
    var id: Long = 0,
    var name: String = ""
)