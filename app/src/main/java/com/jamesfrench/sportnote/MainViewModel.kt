package com.jamesfrench.sportnote

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.jamesfrench.sportnote.database.Training
import com.jamesfrench.sportnote.database.ObjectBox.store

class MainViewModel: ViewModel() {
    private val trainingBox = store.boxFor(Training::class.java)

    var trainings = mutableStateListOf<Training>()
        private set

    init {
        trainings.addAll(trainingBox.all)
    }

    fun addTraining() {
        trainingBox.put(
            Training(name = "Hello, World!")
        )
        updateTrainings()
    }

    fun updateTrainings() {
        trainings.clear()
        trainings.addAll(trainingBox.all)
    }
}