package com.codelab.basiclayouts.ui.screens.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codelab.basiclayouts.R
import com.codelab.basiclayouts.model.Profile
import com.codelab.basiclayouts.ui.components.ConfirmButton
import com.codelab.basiclayouts.ui.components.ForgotPasswordHeadingTextComponent
import com.codelab.basiclayouts.ui.components.ImageComponent
import com.codelab.basiclayouts.ui.components.PasswordConfirmComponent
import com.codelab.basiclayouts.ui.components.TextInfoComponent
import com.codelab.basiclayouts.ui.viewmodel.shared.SignupViewModel

@Composable
fun ResetPasswordScreen(
    navController: NavHostController,
    viewModel: SignupViewModel = hiltViewModel(),
    ) {
    val state by viewModel.state.collectAsState()
    ResetPasswordContent(
        navController = navController,
        signupViewModel = viewModel,
        state = state,
        onChangePassword = viewModel::onChangePassword,
        onChangeComfirmPassword = viewModel::onChangeComfirmPassword,
        onSaveUserInfo = viewModel::onSaveUserInfo
    )
}
@Composable
private fun ResetPasswordContent(
    navController: NavHostController,
    signupViewModel: SignupViewModel,
    state: Profile,
    onChangePassword: (String) -> Unit,
    onChangeComfirmPassword: (String) -> Unit,
    onSaveUserInfo: () -> Unit,
) {
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
                    password = state.password,
                    onPasswordChange = onChangePassword,
                )
                Spacer(modifier = Modifier.height(15.dp))
                PasswordConfirmComponent(
                    labelVal = "Confirm Password",
                    password = state.confirmPassword,
                    onPasswordChange = onChangeComfirmPassword
                )
            }
            ConfirmButton(
                labelVal = "Submit",
                navController = navController,
                signupViewModel = signupViewModel,
                onClick = onSaveUserInfo
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