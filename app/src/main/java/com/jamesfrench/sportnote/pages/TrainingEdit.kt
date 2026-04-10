package com.jamesfrench.sportnote.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jamesfrench.sportnote.MainViewModel
import com.jamesfrench.sportnote.R
import com.jamesfrench.sportnote.components.MainNavigationButton
import com.jamesfrench.sportnote.components.Navigation
import com.jamesfrench.sportnote.components.SecondaryNavigationButton
import com.jamesfrench.sportnote.ui.theme.fontInter
import com.jamesfrench.sportnote.ui.theme.fontJost

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TrainingEdit(leftPadding: Dp, rightPadding: Dp, bottomContentPadding: Dp, viewModel: MainViewModel, navController: NavController) {
    val leftContentPadding = max(17.dp - leftPadding, 0.dp)
    val rightContentPadding = max(17.dp - rightPadding, 0.dp)

    Box(Modifier
        .background(MaterialTheme.colorScheme.background)
        .fillMaxSize()
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize(),
            columns = GridCells.Adaptive(minSize = 300.dp),
            contentPadding = PaddingValues(leftContentPadding, 0.dp, rightContentPadding, (92.dp + max(17.dp - bottomContentPadding, 8.dp))),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(0.dp, 8.dp)
                ) {
                    Text(
                        stringResource(R.string.training),
                        fontFamily = fontJost,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        viewModel.convertTimestampToDate(viewModel.trainingEditSelectedTraining.createdAt, stringResource(R.string.full_date)),
                        fontFamily = fontInter,
                        fontSize = 14.sp,
                    )

                }
            }
            item {
                Text(viewModel.trainingEditSelectedTraining.name)
            }
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