package com.jamesfrench.sportnote

import androidx.compose.runtime.MutableState
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

    fun addTraining() {
        if (selectedTraining.id.toInt() != -1) {
            val returnedTraining = trainingBox.put(
                Training(name = "Hello, World!")
            )
            println(returnedTraining.)
            updateTrainings()
        }
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