package com.jamesfrench.sportnote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jamesfrench.sportnote.viewmodels.DatabaseViewModel

@Composable
fun AddButton(onClick: () -> Unit, icon: Int, iconDescription: String, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier
            .size(75.dp)
            .shadow(4.dp, RoundedCornerShape(18.dp)),
        contentPadding = PaddingValues(0.dp),
        shape = RoundedCornerShape(18.dp),
        elevation = null
    ) {
        Icon(
            painterResource(icon),
            contentDescription = iconDescription,
            modifier = Modifier.size(27.dp),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun NavigationButton(modifier: Modifier = Modifier, icon: Int = 0, iconDescription: String = "") {
    Button(
        onClick = {},
        shape = RoundedCornerShape(13.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            disabledContentColor = MaterialTheme.colorScheme.onSurface
        ),
        modifier = modifier.fillMaxHeight()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painterResource(icon),
                contentDescription = iconDescription,
                modifier = Modifier.size(23.dp),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun Navigation(modifier: Modifier = Modifier, onAddButtonClick: () -> Unit) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Transparent,
                            MaterialTheme.colorScheme.background
                        )
                    )
                )
                .padding(17.dp, 17.dp, 17.dp, 17.dp),
            horizontalArrangement = Arrangement.spacedBy(17.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .height(75.dp)
                    .shadow(4.dp, RoundedCornerShape(18.dp))
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(18.dp))
                    .padding(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                NavigationButton(Modifier.weight(1f), R.drawable.menu, stringResource(R.string.menu))
                NavigationButton(Modifier.weight(1f), R.drawable.chart_no_axes_combined, stringResource(R.string.stats))
                NavigationButton(Modifier.weight(1f), R.drawable.notebook_tabs, stringResource(R.string.exercises))
                NavigationButton(Modifier.weight(1f), R.drawable.search, stringResource(R.string.search))
            }
            AddButton({onAddButtonClick}, R.drawable.diamond_plus, stringResource(R.string.new_training))
        }
    }
}