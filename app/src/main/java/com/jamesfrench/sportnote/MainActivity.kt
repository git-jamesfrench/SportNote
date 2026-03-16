package com.jamesfrench.sportnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jamesfrench.sportnote.components.DropdownMenu
import com.jamesfrench.sportnote.components.DropdownMenuItem
import com.jamesfrench.sportnote.components.MainNavigationButton
import com.jamesfrench.sportnote.components.Navigation
import com.jamesfrench.sportnote.components.NavigationButton
import com.jamesfrench.sportnote.components.NavigationContainer
import com.jamesfrench.sportnote.pages.Home
import com.jamesfrench.sportnote.ui.theme.SportNoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.light(
                scrim = Color.Transparent.toArgb(),
                darkScrim = Color.Transparent.toArgb(),
            )
        )
        setContent {
            SportNoteTheme {
                Scaffold(
                    contentWindowInsets = WindowInsets.safeDrawing,
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        App(innerPadding)
                    }
                }
            }
        }
    }
}

val shadowColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xBF000000) else Color(0x40000000)

@Composable
fun App(innerPadding: PaddingValues) {
    val layoutDirection = LocalLayoutDirection.current
    val leftContentPadding = innerPadding.calculateLeftPadding(layoutDirection)
    val rightContentPadding = innerPadding.calculateRightPadding(layoutDirection)
    val bottomContentPadding = innerPadding.calculateBottomPadding()
    var expanded by remember { mutableStateOf(false) }

    Home(leftContentPadding, rightContentPadding, bottomContentPadding)
    Navigation(leftContentPadding, rightContentPadding, bottomContentPadding) {
        NavigationContainer {
            NavigationButton({expanded = true}, R.drawable.menu, stringResource(R.string.menu)) {
                DropdownMenu(expanded, {expanded = false}, (-5).dp) {
                    DropdownMenuItem(R.string.settings, R.drawable.cog, R.string.settings) {println("Hello, World!")}
                }
            }
            NavigationButton({}, R.drawable.chart_no_axes_combined, stringResource(R.string.stats))
            NavigationButton({}, R.drawable.notebook_tabs, stringResource(R.string.exercises))
            NavigationButton({}, R.drawable.search, stringResource(R.string.search))
        }
        Spacer(Modifier.width(17.dp))
        MainNavigationButton({}, R.drawable.diamond_plus, stringResource(R.string.new_training))
    }
}