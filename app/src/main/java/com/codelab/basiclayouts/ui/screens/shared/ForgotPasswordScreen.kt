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
import com.codelab.basiclayouts.ui.components.ForgotPasswordHeadingTextComponent
import com.codelab.basiclayouts.ui.components.ImageComponent
import com.codelab.basiclayouts.ui.components.MyButton
import com.codelab.basiclayouts.ui.components.MyTextField
import com.codelab.basiclayouts.ui.components.TextInfoComponent
import com.codelab.basiclayouts.ui.viewmodel.shared.ResetPasswordViewModel

@Composable
fun ForgotPasswordScreen(
    navController: NavHostController,
    viewModel: ResetPasswordViewModel,
    ) {
    val state by viewModel.state.collectAsState()
    ForgotPasswordContent(
        navController = navController,
        resetPasswordViewModel = viewModel,
        state = state,
        onChangeEmail = viewModel::onChangeEmail
    )
}

@Composable
private fun ForgotPasswordContent(
    navController: NavHostController,
    resetPasswordViewModel: ResetPasswordViewModel,
    state: Profile,
    onChangeEmail: (String) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        color = Color.White
    ) {
        Column {
            ImageComponent(image = R.drawable.share_baby_mummy)
            Spacer(modifier = Modifier.height(10.dp))
            ForgotPasswordHeadingTextComponent(action = "Forgot")
            TextInfoComponent(
                textVal = "Don't worry, strange things happen. Please enter the email address associated with your account."
            )
            Spacer(modifier = Modifier.height(20.dp))
            MyTextField(
                labelVal = "email ID",
                icon = R.drawable.share_at_symbol,
                onTextChange = onChangeEmail,

            )
            MyButton(labelVal = "Submit", navController,
                resetPasswordViewModel = resetPasswordViewModel,
                onClick = {resetPasswordViewModel.ResetPassword()}
            )
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun ForgotPasswordScreenPreview() {
//    val navController = rememberNavController()
//    ForgotPasswordScreen(navController = navController)
//}