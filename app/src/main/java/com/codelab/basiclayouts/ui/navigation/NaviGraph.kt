package com.codelab.basiclayouts.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codelab.basiclayouts.ui.screens.shared.LoginScreen
//import com.codelab.basiclayouts.ui.screens.shared.ForgotPasswordScreen
//import com.codelab.basiclayouts.ui.screens.shared.ResetPasswordScreen
import com.codelab.basiclayouts.ui.screens.shared.SignupScreen
import com.codelab.basiclayouts.ui.screens.author.AuthorEditStoryScreen
import com.codelab.basiclayouts.ui.screens.author.AuthorMainScreen
import com.codelab.basiclayouts.ui.viewmodel.shared.SignupViewModel

//import com.codelab.basiclayouts.ui.screens.author.AuthorNewStoryScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "LoginScreen"
    ) {
        composable(
            route = "LoginScreen"
        ) {
            LoginScreen(
                navController
            )
        }
        composable(
            route = "ForgotPassword"
        ) {
            //ForgotPasswordScreen(navController)
        }
        composable(route = "ResetPassword") {
            //ResetPasswordScreen(navController)
        }
        composable(
            route = "SignupScreen"
        ) {
            SignupScreen(navController)
        }
    }
}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { AuthorMainScreen(navController) }
//        composable("authorNewStoryScreen") { AuthorNewStoryScreen(navController) }
//        composable("authorEditScreen"){ AuthorEditStoryScreen(navController) }
    }
}