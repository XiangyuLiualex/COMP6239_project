package com.codelab.basiclayouts.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codelab.basiclayouts.ui.screens.author.AuthorEditStoryScreen
import com.codelab.basiclayouts.ui.screens.author.AuthorMainScreen
import com.codelab.basiclayouts.ui.screens.author.AuthorNewStoryScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { AuthorMainScreen(navController) }
        composable("authorNewStoryScreen") { AuthorNewStoryScreen(navController) }
        composable("authorEditScreen"){ AuthorEditStoryScreen(navController) }
    }
}
