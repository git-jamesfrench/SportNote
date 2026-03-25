package com.jamesfrench.sportnote.pages

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jamesfrench.sportnote.MainViewModel
import com.jamesfrench.sportnote.R
import com.jamesfrench.sportnote.components.MainNavigationButton
import com.jamesfrench.sportnote.components.Navigation
import com.jamesfrench.sportnote.components.SecondaryNavigationButton

@Composable
fun TrainingEdit(leftPadding: Dp, rightPadding: Dp, bottomContentPadding: Dp, viewModel: MainViewModel, navController: NavController) {
    Box(Modifier
        .background(MaterialTheme.colorScheme.background)
        .fillMaxSize()
    ) {
        BackHandler {
            viewModel.resetCurrentTraining()
            navController.popBackStack()
        }

        Navigation(leftPadding, rightPadding, bottomContentPadding) {
            Spacer(Modifier.weight(1f))
            SecondaryNavigationButton({
                navController.popBackStack()
            }, R.drawable.square_check_big, stringResource(R.string.finish_training))
            Spacer(Modifier.width(17.dp))
            MainNavigationButton({}, R.drawable.plus, stringResource(R.string.add_exercise))
        }
    }
}