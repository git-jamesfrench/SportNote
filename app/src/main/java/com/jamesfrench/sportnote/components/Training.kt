package com.jamesfrench.sportnote.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@Composable
fun Training(title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .shadow(2.dp, RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(18.dp))
            .padding(17.dp, 12.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            title,
            color = MaterialTheme.colorScheme.onSurface
        )
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