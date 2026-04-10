package com.jamesfrench.sportnote.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jamesfrench.sportnote.MainViewModel
import com.jamesfrench.sportnote.R
import com.jamesfrench.sportnote.database.Training

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TrainingItem(training: Training, onDelete: (training: Training) -> Unit, viewModel: MainViewModel, navController: NavController) {
    val density = LocalDensity.current
    val haptics = LocalHapticFeedback.current
    var expanded by remember { mutableStateOf(false) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(
                MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp),
                RoundedCornerShape(18.dp)
            )
            .clip(RoundedCornerShape(18.dp))
            .indication(interactionSource, ripple())
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { clickOffset ->
                        val press = PressInteraction.Press(clickOffset)
                        interactionSource.emit(press)

                        val released = tryAwaitRelease()

                        interactionSource.emit(
                            if (released) {
                                PressInteraction.Release(press)
                            } else {
                                PressInteraction.Cancel(press)
                            }
                        )
                    },
                    onTap = {
                        viewModel.trainingEditSelectedTraining = training
                        navController.navigate("exercise_edit")
                    },
                    onLongPress = { clickOffset ->
                        haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                        expanded = true
                        offset = clickOffset
                    }
                )
            },
        contentAlignment = Alignment.TopStart
    ) {
        Box {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                offset = with(density) { DpOffset(offset.x.toDp(), offset.y.toDp()) }
            ) {
                DropdownMenuItem(
                    text = stringResource(R.string.delete_training),
                    icon = painterResource(R.drawable.trash),
                    iconDescription = stringResource(R.string.delete_training),
                    onClick = {
                        onDelete(training)
                        expanded = false
                    }
                )
            }
        }
        Column(
            Modifier.padding(17.dp, 12.dp)
        ) {
            Text(
                viewModel.convertTimestampToDate(training.createdAt, stringResource(R.string.full_date)),
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