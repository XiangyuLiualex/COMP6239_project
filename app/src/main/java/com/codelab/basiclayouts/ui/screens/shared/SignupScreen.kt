package com.codelab.basiclayouts.ui.screens.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codelab.basiclayouts.R
import com.codelab.basiclayouts.ui.components.BottomSignupTextComponent
import com.codelab.basiclayouts.ui.components.HeadingTextComponent
import com.codelab.basiclayouts.ui.components.ImageComponent
import com.codelab.basiclayouts.ui.components.SignupButton
import com.codelab.basiclayouts.ui.components.MyTextField
import com.codelab.basiclayouts.ui.components.PasswordConfirmComponent
import com.codelab.basiclayouts.ui.components.SignupTermsAndPrivacyText
import com.codelab.basiclayouts.ui.viewmodel.shared.SignupViewModel

@Composable
fun SignupScreen(navController: NavHostController) {
    val signupViewModel = viewModel<SignupViewModel>()
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        color = Color.White
    ) {
        Column {
            ImageComponent(image = R.drawable.share_black_cat)
            Spacer(modifier = Modifier.height(10.dp))
            HeadingTextComponent(heading = "Sign Up")
            Spacer(modifier = Modifier.height(20.dp))
            Column {
                MyTextField(labelVal = "Username", icon = R.drawable.share_lockperson)
                Spacer(modifier = Modifier.height(15.dp))
                PasswordConfirmComponent(
                    labelVal = "Password",
                    password = signupViewModel.password,
                    onPasswordChange = { newPassword -> signupViewModel.password = newPassword },
                )
                Spacer(modifier = Modifier.height(15.dp))
                PasswordConfirmComponent(
                    labelVal = "Confirm Password",
                    password = signupViewModel.confirmPassword,
                    onPasswordChange = { newPassword -> signupViewModel.confirmPassword = newPassword },
                )
                Spacer(modifier = Modifier.height(15.dp))
                MyTextField(labelVal = "email ID", icon = R.drawable.share_at_symbol)
            }
            Spacer(modifier = Modifier.height(20.dp))
            SignupTermsAndPrivacyText()
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomStart
            ) {
                Column {
                    SignupButton(
                        labelVal = "Continue",
                        navController = navController,
                        signupViewModel = signupViewModel
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    BottomSignupTextComponent(navController)
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignupScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Signup") {
        composable("Signup") {
            SignupScreen(navController = navController)
        }
        // Define other destinations here
    }
}