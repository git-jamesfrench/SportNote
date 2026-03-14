package com.jamesfrench.sportnote.pages

import android.widget.GridLayout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.jamesfrench.sportnote.components.Training
import com.jamesfrench.sportnote.ui.theme.SportNoteTheme

@Composable
fun Home(leftPadding: Dp, rightPadding: Dp) {
    val leftContentPadding = max(17.dp - leftPadding, 0.dp)
    val rightContentPadding = max(17.dp - rightPadding, 0.dp)

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
            //.verticalScroll(rememberScrollState()),
        columns = GridCells.Adaptive(minSize = 300.dp),
        contentPadding = PaddingValues(leftContentPadding, 0.dp, rightContentPadding, 0.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(9.dp)
    ) {
        items(20) { index ->
            Training("Hey, $index!")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121318)
@Composable
fun Preview() {
    SportNoteTheme(darkTheme = true) {
        Home(0.dp,0.dp)
    }
}