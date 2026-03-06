package com.jamesfrench.sportnote

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jamesfrench.sportnote.ui.theme.SportNoteTheme
import com.jamesfrench.sportnote.ui.theme.fontJost

class MainActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContent {
            SportNoteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        Navigation()
                        MainPage()
                    }
                }
            }
        }
    }
}

@Composable
fun MainPage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(17.dp, 17.dp, 17.dp, 0.dp),
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
                fontFamily = fontJost,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Text(
            stringResource(R.string.recent),
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = fontJost
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(2.dp, RoundedCornerShape(18.dp))
                .background(MaterialTheme.colorScheme.surfaceContainer, RoundedCornerShape(18.dp))
                .padding(17.dp, 12.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Exercise()
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            )
            Exercise()
        }
    }
}

@Composable
fun Exercise() {
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
            Text(
                "24kg",
                fontFamily = fontJost,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceContainerHighest, RoundedCornerShape(5.dp))
                    .padding(3.dp, 0.dp)
            )
            Text(
                "×",
                fontFamily = fontJost,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                "10",
                fontFamily = fontJost,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                "×",
                fontFamily = fontJost,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                "4s",
                fontFamily = fontJost,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

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
fun NavigationButton(icon: Int = 0, iconDescription: String = "", modifier: Modifier = Modifier) {
    Button(
        onClick = {},
        shape = RoundedCornerShape(13.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceContainer,
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
fun Navigation(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(17.dp, 17.dp, 17.dp, 17.dp),
        horizontalArrangement = Arrangement.spacedBy(17.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .height(75.dp)
                .shadow(4.dp, RoundedCornerShape(18.dp))
                .background(MaterialTheme.colorScheme.surfaceContainer, RoundedCornerShape(18.dp))
                .padding(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            NavigationButton(R.drawable.menu, stringResource(R.string.menu), modifier = Modifier.weight(1f))
            NavigationButton(R.drawable.chart_no_axes_combined, stringResource(R.string.stats), Modifier.weight(1f))
            NavigationButton(R.drawable.notebook_tabs, stringResource(R.string.exercises), Modifier.weight(1f))
            NavigationButton(R.drawable.search, stringResource(R.string.search), Modifier.weight(1f))
        }
        AddButton({}, R.drawable.diamond_plus, stringResource(R.string.new_training))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121318)
@Composable
fun GreetingPreview() {
    SportNoteTheme(darkTheme = true) {
        Navigation()
        MainPage()
    }
}