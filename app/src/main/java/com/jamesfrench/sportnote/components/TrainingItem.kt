package com.jamesfrench.sportnote.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jamesfrench.sportnote.App
import com.jamesfrench.sportnote.ui.theme.SportNoteTheme

@Composable
fun TrainingItem(title: String?) {
    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp), RoundedCornerShape(18.dp))
            .clickable(true, onClick = {})
            .padding(17.dp, 12.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        if (title != null) {
            Text(
                title,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        //Exercise()
        //Spacer(
        //    modifier = Modifier
        //        .height(1.dp)
        //        .fillMaxWidth()
        //        .background(MaterialTheme.colorScheme.surfaceContainerHigh)
        //)
        //Exercise()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121318)
@Composable
fun Preview() {
    SportNoteTheme(darkTheme = true) {
        App(PaddingValues(0.dp))
    }
}