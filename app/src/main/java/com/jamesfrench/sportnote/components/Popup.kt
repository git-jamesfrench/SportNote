package com.jamesfrench.sportnote.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jamesfrench.sportnote.R
import com.jamesfrench.sportnote.ui.theme.SportNoteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Popup(icon: Painter, iconDescription: String, title: String, text: String, showDialog: Boolean, onDismissRequest: () -> Unit, iconTint: Color = MaterialTheme.colorScheme.secondary, content: @Composable () -> Unit) {
    if (showDialog) {
        BasicAlertDialog(
            onDismissRequest = onDismissRequest
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceColorAtElevation(5.dp), RoundedCornerShape(32.dp))
                    .widthIn(max = 350.dp)
                    .wrapContentHeight()
                    .padding(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        icon,
                        contentDescription = iconDescription,
                        modifier = Modifier.size(24.dp),
                        tint = iconTint
                    )
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier =
                        Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    content()
                }
            }
        }
    }
}

@Composable
fun PopupButton(text: String, color: Color = MaterialTheme.colorScheme.onSurface, onPress: () -> Unit) {
    Box(
        Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colorScheme.surfaceTint, RoundedCornerShape(4.dp))
            .clickable(
                onClick = onPress
            )
            .fillMaxWidth()
            .padding(0.dp, 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = color)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121318)
@Composable
fun Preview() {
    SportNoteTheme(darkTheme = true) {
        Popup(
            painterResource(R.drawable.trash),
            stringResource(R.string.delete_training),
            stringResource(R.string.delete_training_popup_title),
            stringResource(R.string.delete_training_popup_description),
            true,
            { },
            iconTint = MaterialTheme.colorScheme.error
        ) {
            PopupButton("Confirmer", color = MaterialTheme.colorScheme.error) {}
            PopupButton("Annuler") {}
        }
    }
}