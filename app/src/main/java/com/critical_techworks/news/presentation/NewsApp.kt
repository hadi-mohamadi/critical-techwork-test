package com.critical_techworks.news.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.critical_techworks.news.presentation.theme.NewsTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalPermissionsApi
@Composable
fun NewsApp() {
    NewsTheme {
        val systemUiController = rememberSystemUiController()
        val darkIcons = MaterialTheme.colors.isLight
        SideEffect {
            systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
        }
        Scaffold {
            ArticlesNavGraph(modifier = Modifier.padding(it))
        }
    }
}
