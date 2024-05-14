package com.codelab.basiclayouts.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.codelab.basiclayouts.ui.screens.author.AuthorMainScreen
import com.codelab.basiclayouts.ui.screens.reader.ReaderLibraryScreen
import com.codelab.basiclayouts.ui.screens.reader.StoryContentScreen
import com.codelab.basiclayouts.ui.screens.author.AuthorScreen
import com.codelab.basiclayouts.ui.screens.shared.LoginScreen
import com.codelab.basiclayouts.ui.screens.shared.ForgotPasswordScreen
import com.codelab.basiclayouts.ui.screens.shared.ResetPasswordScreen
import com.codelab.basiclayouts.ui.screens.shared.SignupScreen
import com.codelab.basiclayouts.ui.screens.shared.GuestScreen
import com.codelab.basiclayouts.ui.screens.shared.MainScreen
import com.codelab.basiclayouts.ui.screens.shared.ProfileScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "library"//"MainScreen"
    ) {
        composable(route = "LoginScreen") {
            LoginScreen(navController)
        }
        composable(route = "ForgotPassword") {
            ForgotPasswordScreen(navController)
        }
        composable(route = "ResetPassword") {
            ResetPasswordScreen(navController)
        }
        composable(route = "SignupScreen") {
            SignupScreen(navController)
        }
        composable(route = "MainScreen") {
            MainScreen(navController)
        }
        composable(route = "GuestScreen") {
            GuestScreen(navController)
        }
        composable(route = "ProfileScreen") {
            ProfileScreen(navController)
        }
        composable(route = "author_home_Screen") {
            AuthorScreen(navController)
        }
        //下面是新加的导航
        composable("library") {
            ReaderLibraryScreen(navController = navController)
        }
        composable(
            route = "storyContent/{readerId}/{storyId}/{chapterId}/{currentReadingPathId}",
        ) { backStackEntry ->
            val readerId = backStackEntry.arguments?.getString("readerId")?.toInt() ?: 0
            val storyId = backStackEntry.arguments?.getString("storyId")?.toInt() ?: 0
            val chapterId = backStackEntry.arguments?.getString("chapterId")?.toInt() ?: 0
            val currentReadingPathId = backStackEntry.arguments?.getString("currentReadingPathId")?.toInt() ?: 0
            StoryContentScreen(readerId = readerId,storyId = storyId, chapterId = chapterId, currentReadingPathId = currentReadingPathId)
        }
    }
}