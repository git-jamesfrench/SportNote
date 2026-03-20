package com.jamesfrench.sportnote.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.jamesfrench.sportnote.App
import com.jamesfrench.sportnote.MainViewModel
import com.jamesfrench.sportnote.R
import com.jamesfrench.sportnote.components.DropdownMenu
import com.jamesfrench.sportnote.components.DropdownMenuItem
import com.jamesfrench.sportnote.components.MainNavigationButton
import com.jamesfrench.sportnote.components.Navigation
import com.jamesfrench.sportnote.components.NavigationButton
import com.jamesfrench.sportnote.components.NavigationContainer
import com.jamesfrench.sportnote.components.TrainingItem
import com.jamesfrench.sportnote.ui.theme.SportNoteTheme

@Composable
fun Home(leftPadding: Dp, rightPadding: Dp, bottomContentPadding: Dp, viewModel: MainViewModel) {
    val leftContentPadding = max(17.dp - leftPadding, 0.dp)
    val rightContentPadding = max(17.dp - rightPadding, 0.dp)
    var expanded by remember { mutableStateOf(false) }

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Adaptive(minSize = 300.dp),
        contentPadding = PaddingValues(leftContentPadding, 0.dp, rightContentPadding, (92.dp + max(17.dp - bottomContentPadding, 8.dp))),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(viewModel.trainings) { training ->
            TrainingItem(training, viewModel)
        }
    }
    Navigation(leftPadding, rightPadding, bottomContentPadding) {
        NavigationContainer {
            NavigationButton({expanded = true}, R.drawable.menu, stringResource(R.string.menu)) {
                DropdownMenu(expanded, {expanded = false}, DpOffset((-5).dp, 0.dp)) {
                    DropdownMenuItem(stringResource(R.string.settings), painterResource(R.drawable.cog), stringResource(R.string.settings), false) {println("Hello, World!")}
                }
            }
            NavigationButton({}, R.drawable.chart_no_axes_combined, stringResource(R.string.stats), false)
            NavigationButton({}, R.drawable.notebook_tabs, stringResource(R.string.exercises), false)
            NavigationButton({}, R.drawable.search, stringResource(R.string.search), false)
        }
        Spacer(Modifier.width(17.dp))
        MainNavigationButton({viewModel.addTraining()}, R.drawable.diamond_plus, stringResource(R.string.new_training))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121318)
@Composable
fun Preview() {
    SportNoteTheme(darkTheme = true) {
        App(PaddingValues(0.dp))
    }
}