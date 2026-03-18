package com.jamesfrench.sportnote.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun DropdownMenu(expanded: Boolean, onDismissRequest: () -> Unit, offset: DpOffset = DpOffset(0.dp, 0.dp), content: @Composable () -> Unit) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        shape = RoundedCornerShape(18.dp),
        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(5.dp),
        offset = offset,
        shadowElevation = 2.dp
    ) {
        content()
    }
}

@Composable
fun DropdownMenuItem(text: String, icon: Painter, iconDescription: String, onClick: () -> Unit) {
    DropdownMenuItem(
        text = { Text(text) },
        leadingIcon = {
            Icon(
                icon,
                contentDescription = iconDescription,
                tint = MaterialTheme.colorScheme.onSurface
            )
        },
        contentPadding = PaddingValues(16.dp, 0.dp),
        onClick = onClick
    )
}