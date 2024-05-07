package com.codelab.basiclayouts.ui.screens.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codelab.basiclayouts.R
import com.codelab.basiclayouts.ui.components.ConfirmButton
import com.codelab.basiclayouts.ui.components.ForgotPasswordHeadingTextComponent
import com.codelab.basiclayouts.ui.components.ImageComponent
import com.codelab.basiclayouts.ui.components.PasswordConfirmComponent
import com.codelab.basiclayouts.ui.components.TextInfoComponent
import com.codelab.basiclayouts.ui.viewmodel.shared.ResetPasswordViewModel
import com.codelab.basiclayouts.ui.viewmodel.shared.SignupViewModel

@Composable
fun ResetPasswordScreen(navController: NavHostController) {
    val resetPasswordViewModel = viewModel<ResetPasswordViewModel>()
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column {
            ImageComponent(image = R.drawable.share_chunky_bat)
            Spacer(modifier = Modifier.height(10.dp))
            ForgotPasswordHeadingTextComponent(action = "Reset")
            TextInfoComponent(
                textVal = "Don't worry, strange things happen. Please enter the email address associated with your account."
            )
            Spacer(modifier = Modifier.height(20.dp))
            Column {
                PasswordConfirmComponent(
                    labelVal = "Password",
                    password = resetPasswordViewModel.password,
                    onPasswordChange = { newPassword -> resetPasswordViewModel.password = newPassword },
                )
                Spacer(modifier = Modifier.height(15.dp))
                PasswordConfirmComponent(
                    labelVal = "Confirm Password",
                    password = resetPasswordViewModel.confirmPassword,
                    onPasswordChange = { newPassword -> resetPasswordViewModel.confirmPassword = newPassword },
                )
            }
            val signupViewModel = viewModel<SignupViewModel>()
            ConfirmButton(
                labelVal = "Submit",
                navController = navController,
                signupViewModel = signupViewModel,
                resetPasswordViewModel = resetPasswordViewModel
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ResetPasswordScreenPreview() {
    val navController = rememberNavController()
    ResetPasswordScreen(navController = navController)
}