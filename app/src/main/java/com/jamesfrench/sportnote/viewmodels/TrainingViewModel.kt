package com.jamesfrench.sportnote.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jamesfrench.sportnote.database.Training
import com.jamesfrench.sportnote.database.TrainingDao
import kotlinx.coroutines.launch

class TrainingViewModel (
    private val trainingDao: TrainingDao
): ViewModel() {

    val trainings = trainingDao.getTrainings()

    fun addTraining(title: String) {
        viewModelScope.launch {
            trainingDao.insertTraining(Training(
                title = title,
                note = "",
                timestamp = 0,
            ))
        }
    }
}

class TrainingViewModelFactory(
    private val trainingDao: TrainingDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TrainingViewModel(trainingDao) as T
    }
}