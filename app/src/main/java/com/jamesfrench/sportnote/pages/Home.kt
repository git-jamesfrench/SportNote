package com.jamesfrench.sportnote.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(leftContentPadding, 0.dp, rightContentPadding, 0.dp), // Horizontal 17.dp
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            repeat(20) {
                Training("Hey, world!")
            }
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