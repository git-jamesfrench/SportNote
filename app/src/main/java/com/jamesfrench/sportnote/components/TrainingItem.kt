package com.jamesfrench.sportnote.components

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jamesfrench.sportnote.App
import com.jamesfrench.sportnote.MainViewModel
import com.jamesfrench.sportnote.R
import com.jamesfrench.sportnote.database.Training
import com.jamesfrench.sportnote.ui.theme.SportNoteTheme

@Composable
fun TrainingItem(training: Training, viewModel: MainViewModel) {
    val haptics = LocalHapticFeedback.current
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(
                MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp),
                RoundedCornerShape(18.dp)
            )
            .clip(RoundedCornerShape(18.dp))
            .combinedClickable(
                onClick = {},
                onLongClick = {
                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                    expanded = true
                }
            )
            .padding(17.dp, 12.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Column {
            Text(
                training.name,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }, //with(density) { DpOffset(offset.x.toDp(), offset.y.toDp()) }
        ) {
            DropdownMenuItem(
                text = stringResource(R.string.delete_training),
                icon = painterResource(R.drawable.trash),
                iconDescription = stringResource(R.string.delete_training),
                onClick = {
                    viewModel.delTraining(training.id)
                    expanded = false
                }
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