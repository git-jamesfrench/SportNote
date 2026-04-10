package com.jamesfrench.sportnote

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jamesfrench.sportnote.database.ObjectBox.store
import com.jamesfrench.sportnote.database.Training
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class MainViewModel: ViewModel() {
    // Variables
    private val trainingBox = store.boxFor(Training::class.java)

    var trainingEditSelectedTraining by mutableStateOf(Training(id = -1))

    var trainings = mutableStateListOf<Training>() // Temporary, needs a page system!
        private set

    var isCreatingTraining by mutableStateOf(false)

    // Functions
    init {
        trainings.addAll(trainingBox.all)
    }

    fun addTraining(): Boolean {
        if (!isCreatingTraining) {
            val newTrainingID = trainingBox.put(
                Training(
                    name = "Hello, World!",
                    createdAt = System.currentTimeMillis()
                )
            )
            trainingEditSelectedTraining = trainingBox.get(newTrainingID)
            isCreatingTraining = true
            updateTrainings()
            return true
        }
        return false
    }

    fun updateTrainings() {
        trainings.clear()
        trainings.addAll(trainingBox.all)
    }

    fun delTraining(id: Long) {
        trainingBox.remove(id)
        updateTrainings()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertTimestampToDate(timestamp: Long, pattern: String): String {
        return Instant
            .ofEpochSecond(timestamp / 1000)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
            .format(
                DateTimeFormatter
                    .ofPattern(pattern)
            )
    }
}