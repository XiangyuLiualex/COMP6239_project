package code.ui.screens.shared

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.codelab.basiclayouts.R
import code.model.Profile
import code.ui.components.ForgotPasswordHeadingTextComponent
import code.ui.components.ImageComponent
import code.ui.components.MyButton
import code.ui.components.MyTextField
import code.ui.components.TextInfoComponent
import code.ui.viewmodel.shared.ResetPasswordViewModel

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