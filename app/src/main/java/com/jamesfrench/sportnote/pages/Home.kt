package com.jamesfrench.sportnote.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.jamesfrench.sportnote.MainViewModel
import com.jamesfrench.sportnote.R
import com.jamesfrench.sportnote.components.DropdownMenu
import com.jamesfrench.sportnote.components.DropdownMenuItem
import com.jamesfrench.sportnote.components.MainNavigationButton
import com.jamesfrench.sportnote.components.Navigation
import com.jamesfrench.sportnote.components.NavigationButton
import com.jamesfrench.sportnote.components.NavigationContainer
import com.jamesfrench.sportnote.components.Popup
import com.jamesfrench.sportnote.components.PopupButton
import com.jamesfrench.sportnote.components.TrainingItem
import com.jamesfrench.sportnote.database.Training
import com.jamesfrench.sportnote.ui.theme.fontJost
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Home(leftPadding: Dp, rightPadding: Dp, bottomContentPadding: Dp, viewModel: MainViewModel, navController: NavController) {
    val leftContentPadding = max(17.dp - leftPadding, 0.dp)
    val rightContentPadding = max(17.dp - rightPadding, 0.dp)
    var expanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val notImplementedString = stringResource(R.string.not_implemented)

    val trainingToDelete = remember { mutableStateOf(Training()) }
    val trainingDeleteDialog = remember { mutableStateOf(false) }

    var trainings by remember { mutableStateOf<List<Training>>(emptyList()) }

    LaunchedEffect(Unit) {
        viewModel.isCreatingTraining = false
        trainings = viewModel.getTrainings()
    }

    SnackbarHost(
        hostState = snackbarHostState,
        modifier = Modifier
            .padding(leftContentPadding, 0.dp, rightContentPadding, 0.dp)
            .zIndex(5f)
    ) { msg ->
        Snackbar(
            msg,
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(7.dp),
            contentColor = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(0.dp)
        )
    }
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Adaptive(minSize = 300.dp),
        contentPadding = PaddingValues(leftContentPadding, 0.dp, rightContentPadding, (92.dp + max(17.dp - bottomContentPadding, 8.dp))),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(span = { GridItemSpan(maxCurrentLineSpan) }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(0.dp, 8.dp)
            ) {
                Icon(
                    painterResource(R.drawable.notebook_dumbbell),
                    stringResource(R.string.app_name),
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    stringResource(R.string.app_name),
                    fontFamily = fontJost,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.weight(1f))
                Text(
                    stringResource(R.string.recent),
                )
            }
        }
        items(trainings) { training ->
            TrainingItem(training, { training ->
                trainingToDelete.value = training
                trainingDeleteDialog.value = true
            }, viewModel, navController)
        }
    }

    // Dialog
    Popup(
        painterResource(R.drawable.trash),
        stringResource(R.string.delete_training),
        stringResource(R.string.delete_training_popup_title),
        stringResource(R.string.delete_training_popup_description),
        trainingDeleteDialog.value,
        { trainingDeleteDialog.value = false},
        MaterialTheme.colorScheme.error
    ) {
        PopupButton("Confirmer", color = MaterialTheme.colorScheme.error) {
            viewModel.delTraining(trainingToDelete.value.id)
            trainingDeleteDialog.value = false
            trainings = viewModel.getTrainings()
        }
        PopupButton("Annuler") {
            trainingDeleteDialog.value = false
        }
    }

    // Navigation
    Navigation(leftPadding, rightPadding, bottomContentPadding) {
        NavigationContainer {
            NavigationButton({expanded = true}, R.drawable.menu, stringResource(R.string.menu)) {
                DropdownMenu(expanded, {expanded = false}, DpOffset((-5).dp, 0.dp)) {
                    DropdownMenuItem(stringResource(R.string.settings), painterResource(R.drawable.cog), stringResource(R.string.settings), true) {
                        scope.launch {
                            snackbarHostState.showSnackbar(notImplementedString)
                        }
                        expanded = false
                    }
                }
            }
            NavigationButton({
                scope.launch {
                    snackbarHostState.showSnackbar(notImplementedString)
                }
            }, R.drawable.chart_no_axes_combined, stringResource(R.string.stats), true)
            NavigationButton({
                scope.launch {
                    snackbarHostState.showSnackbar(notImplementedString)
                }
            }, R.drawable.notebook_tabs, stringResource(R.string.exercises), true)
            NavigationButton({
                scope.launch {
                    snackbarHostState.showSnackbar(notImplementedString)
                }
            }, R.drawable.search, stringResource(R.string.search), true)
        }
        Spacer(Modifier.width(17.dp))
        MainNavigationButton({
            if (viewModel.addTraining()) {
                navController.navigate("exercise_edit")
            }
        }, R.drawable.diamond_plus, stringResource(R.string.new_training))
    }
}