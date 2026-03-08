package com.jamesfrench.sportnote

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jamesfrench.sportnote.ui.theme.TrainingTypo
import com.jamesfrench.sportnote.ui.theme.Typo
import com.jamesfrench.sportnote.ui.theme.fontJost
import com.jamesfrench.sportnote.viewmodels.DatabaseViewModel

@Composable
fun MainPage(modifier: Modifier = Modifier, viewModel: DatabaseViewModel) {
    val trainings by viewModel.trainings.collectAsState(initial = emptyList())

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(17.dp, 17.dp, 17.dp, (75 + 17 * 2).dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painterResource(R.drawable.notebook_dumbbell),
                contentDescription = stringResource(R.string.app_name),
                modifier = Modifier.size(35.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                stringResource(R.string.app_name),
                style = Typo.displayLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
        Text(
            stringResource(R.string.recent),
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = fontJost
        )
        trainings.forEach { trainingWithExercises ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(2.dp, RoundedCornerShape(18.dp))
                    .clickable(onClick = { viewModel.delTraining(trainingWithExercises.training) })
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(18.dp))
                    .padding(17.dp, 12.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                trainingWithExercises.exercises.forEachIndexed { index, exercise ->
                    Exercise(exercise.primary, exercise.amount, exercise.series, exercise.duration)
                    if (index < trainingWithExercises.exercises.lastIndex) {
                        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun Exercise(primary: Int? = null, amount: Int? = null, series: Int? = null, duration: Long? = null) {
    Row(
        modifier = Modifier.
        height(30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Icon(
            painterResource(R.drawable.dumbbell),
            contentDescription = "Musculation exercise",
            modifier = Modifier.size(19.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            "Musculation Titre",
            fontFamily = fontJost,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {

            if (primary != null) {
                Text(
                    primary.toString() + "kg",
                    style = TrainingTypo,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.surfaceColorAtElevation(20.dp),
                            RoundedCornerShape(5.dp)
                        )
                        .padding(3.dp, 3.dp)
                )
            }
            if (amount != null) {
                if (primary != null) {
                    Text(
                        "×",
                        fontFamily = fontJost,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Text(
                    amount.toString(),
                    fontFamily = fontJost,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            if (series != null || duration != null) {
                if (amount != null || primary != null) {
                    Text(
                        "×",
                        fontFamily = fontJost,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Text(
                    series.toString(),
                    fontFamily = fontJost,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}