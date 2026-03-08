package com.jamesfrench.sportnote.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jamesfrench.sportnote.data.Training
import com.jamesfrench.sportnote.database.TrainingDao
import kotlinx.coroutines.launch

class DatabaseViewModel (
    private val trainingDao: TrainingDao
): ViewModel() {

    val trainings = trainingDao.getTrainings()

    fun delTraining(training: Training) {
        viewModelScope.launch {
            trainingDao.deleteTraining(training)
        }
    }

    suspend fun addTraining(title: String): Long {
        return trainingDao.insertTraining(Training(
            title = title,
            note = "",
            timestamp = 0,
        ))
    }
}

@Suppress("UNCHECKED_CAST")
class DatabaseViewModelFactory(
    private val trainingDao: TrainingDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DatabaseViewModel(trainingDao) as T
    }
}