package com.jamesfrench.sportnote

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jamesfrench.sportnote.database.ObjectBox.store
import com.jamesfrench.sportnote.database.Training

class MainViewModel: ViewModel() {
    private val trainingBox = store.boxFor(Training::class.java)

    var selectedTraining by mutableStateOf(Training(id = -1))

    var trainings = mutableStateListOf<Training>()
        private set

    init {
        trainings.addAll(trainingBox.all)
    }

    fun addTraining(): Boolean {
        if (selectedTraining.id == -1L) {
            val newTrainingID = trainingBox.put(
                Training(name = "Hello, World!")
            )
            selectedTraining = trainingBox.get(newTrainingID)
            updateTrainings()
            return true
        }
        return false
    }

    fun resetCurrentTraining() {
        selectedTraining = Training(id = -1)
    }

    fun updateTrainings() {
        trainings.clear()
        trainings.addAll(trainingBox.all)
    }

    fun delTraining(id: Long) {
        trainingBox.remove(id)
        updateTrainings()
    }
}