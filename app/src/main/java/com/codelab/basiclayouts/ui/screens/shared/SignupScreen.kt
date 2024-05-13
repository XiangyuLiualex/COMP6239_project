package com.codelab.basiclayouts.ui.screens.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codelab.basiclayouts.R
import com.codelab.basiclayouts.model.Profile
import com.codelab.basiclayouts.ui.components.BottomSignupTextComponent
import com.codelab.basiclayouts.ui.components.HeadingTextComponent
import com.codelab.basiclayouts.ui.components.ImageComponent
import com.codelab.basiclayouts.ui.components.ConfirmButton
import com.codelab.basiclayouts.ui.components.MyTextField
import com.codelab.basiclayouts.ui.components.PasswordConfirmComponent
import com.codelab.basiclayouts.ui.components.SignupTermsAndPrivacyText
import com.codelab.basiclayouts.ui.viewmodel.shared.SignupViewModel

@Composable
fun SignupScreen(
    navController: NavHostController,
    viewModel: SignupViewModel = hiltViewModel(),
    ) {
    val state by viewModel.state.collectAsState()
    SignupContent(
        navController = navController,
        signupViewModel = viewModel,
        state = state,
        onChangeUsername = viewModel::onChangeUsername,
        onChangePassword = viewModel::onChangePassword,
        onChangeComfirmPassword = viewModel::onChangeComfirmPassword,
        onChangeEmail = viewModel::onChangeEmail,
        onSaveUserInfo = viewModel::onSaveUserInfo
    )
}

@Composable
private fun SignupContent(
    navController: NavHostController,
    signupViewModel: SignupViewModel,
    state: Profile,
    onChangeUsername: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    onChangeComfirmPassword: (String) -> Unit,
    onChangeEmail: (String) -> Unit,
    onSaveUserInfo: () -> Unit,
) {
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
                MyTextField(
                    labelVal = "Username",
                    icon = R.drawable.share_lockperson,
                    onTextChange = onChangeUsername
                )
                Spacer(modifier = Modifier.height(15.dp))
                PasswordConfirmComponent(
                    labelVal = "Password",
                    password = state.password,
                    onPasswordChange = onChangePassword,
                )
                Spacer(modifier = Modifier.height(15.dp))
                PasswordConfirmComponent(
                    labelVal = "Confirm Password",
                    password = state.confirmPassword,
                    onPasswordChange = onChangeComfirmPassword,
                )
                Spacer(modifier = Modifier.height(15.dp))
                MyTextField(
                    labelVal = "email ID",
                    icon = R.drawable.share_at_symbol,
                    onTextChange = onChangeEmail
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            SignupTermsAndPrivacyText()
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomStart
            ) {
                Column {
                    ConfirmButton(
                        labelVal = "save",
                        navController = navController,
                        signupViewModel = signupViewModel,
                        onClick = onSaveUserInfo
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
fun SignupScreenPreview() {
    val navController = rememberNavController()
    SignupScreen(navController = navController)
}