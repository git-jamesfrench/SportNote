package com.jamesfrench.sportnote.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.jamesfrench.sportnote.App
import com.jamesfrench.sportnote.MainViewModel
import com.jamesfrench.sportnote.components.TrainingItem
import com.jamesfrench.sportnote.ui.theme.SportNoteTheme

@Composable
fun Home(leftPadding: Dp, rightPadding: Dp, bottomContentPadding: Dp, viewModel: MainViewModel) {
    val leftContentPadding = max(17.dp - leftPadding, 0.dp)
    val rightContentPadding = max(17.dp - rightPadding, 0.dp)

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Adaptive(minSize = 300.dp),
        contentPadding = PaddingValues(leftContentPadding, 0.dp, rightContentPadding, (92.dp + max(17.dp - bottomContentPadding, 8.dp))),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(viewModel.trainings) { training ->
            TrainingItem(title = training.name) { viewModel.delTraining(training.id) }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121318)
@Composable
fun Preview() {
    SportNoteTheme(darkTheme = true) {
        App(PaddingValues(0.dp))
    }
}