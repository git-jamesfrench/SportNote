package com.jamesfrench.sportnote

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.jamesfrench.sportnote.database.MainDatabase
import com.jamesfrench.sportnote.ui.theme.SportNoteTheme
import com.jamesfrench.sportnote.ui.theme.TrainingTypo
import com.jamesfrench.sportnote.ui.theme.Typo
import com.jamesfrench.sportnote.ui.theme.fontJost
import com.jamesfrench.sportnote.viewmodels.DatabaseViewModel
import com.jamesfrench.sportnote.viewmodels.DatabaseViewModelFactory

class MainActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        val db = Room.databaseBuilder(
            applicationContext,
            MainDatabase::class.java,
            "database"
        ).build()

        val trainingDao = db.trainingDao()

        val databaseViewModel: DatabaseViewModel by viewModels {
            DatabaseViewModelFactory(trainingDao)
        }

        setContent {
            SportNoteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        App(databaseViewModel = databaseViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun App(databaseViewModel: DatabaseViewModel) {
    val navController = rememberNavController()
    var currentTrainingID: Long by remember { mutableLongStateOf(0) }

    NavHost(
        navController = navController,
        startDestination = "home",
        enterTransition = { EnterTransition.None },
        exitTransition =  { ExitTransition.KeepUntilTransitionsFinished },
        popEnterTransition =  { EnterTransition.None },
        popExitTransition = { ExitTransition.KeepUntilTransitionsFinished },
    ) {
        composable(
            "home",
        ) {
            Navigation(
                modifier = Modifier.zIndex(2f),
                onAddButtonClick = {
                    val id = databaseViewModel.addTraining("Hello, World!")
                    currentTrainingID = id
                }
            )
            MainPage(viewModel = databaseViewModel)
        }
        composable(
            "training",
        ) {
            Navigation(
                modifier = Modifier.zIndex(2f),
                onAddButtonClick = {
                    databaseViewModel.addTraining("Hello, World!")
                }
            )
        }
    }
}