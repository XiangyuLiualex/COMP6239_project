package code.ui.screens.shared

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codelab.basiclayouts.R
import code.ui.components.*
import code.ui.viewmodel.shared.Identity
import code.ui.viewmodel.shared.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = viewModel() // 使用普通 ViewModel
) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val selectedIdentity by viewModel.selectedIdentity.collectAsState()
    val prove by viewModel.prove.collectAsState()

    LoginContent(
        navController = navController,
        email = email,
        password = password,
        prove=prove,
        selectedIdentity = selectedIdentity,
        onChangeEmail = viewModel::onEmailChange,
        onChangePassword = viewModel::onPasswordChange,
        onChangeIdentity = viewModel::onIdentityChange,
        viewModel
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun LoginContent(
    navController: NavHostController,
    email: String,
    password: String,
    prove:Boolean,
    selectedIdentity: Identity,
    onChangeEmail: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    onChangeIdentity: (Identity) -> Unit,
    viewModel: LoginViewModel
) {
    var emailState by remember { mutableStateOf(email) }
    var passwordState by remember { mutableStateOf(password) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        color = Color.White
    ) {
        Column {
            ImageComponent(image = R.drawable.share_sweet_franky)
            Spacer(modifier = Modifier.height(10.dp))
            HeadingTextComponent(heading = "Login")
            Spacer(modifier = Modifier.height(20.dp))
            Column {
                MyTextField(
                    labelVal = "email ID",
                    R.drawable.share_at_symbol,
                    onTextChange = {
                        emailState = it
                        onChangeEmail(it)
                    }
                )
                Spacer(modifier = Modifier.height(15.dp))
                PasswordInputComponent(labelVal = "Password", onChangePassword = {
                    passwordState = it
                    onChangePassword(it)
                })
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ForgotPasswordTextComponent(navController)
                }
                IdentityOptions(
                    selectedIdentity = selectedIdentity,
                    onSelectIdentity = onChangeIdentity
                )
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.TopStart
                ) {
                    Column {
                        MainPageButton(
                            isProve=prove,
                            labelVal = "Continue",
                            identity = selectedIdentity,
                            navController = navController,
                            onclick = { viewModel.readProfile()
//                                viewModel.checkValue()
                            }
                        )
                        BottomComponent()
                        Spacer(modifier = Modifier.height(5.dp))
                        BottomLoginTextComponent(
                            initialText = "        Haven't we seen you around here before? ",
                            action = "Join us!",
                            navController
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun IdentityOptions(
    selectedIdentity: Identity,
    onSelectIdentity: (Identity) -> Unit
) {
    Column {
        RadioOption(
            text = "Reader",
            selected = selectedIdentity == Identity.READER,
            onSelect = { onSelectIdentity(Identity.READER) }
        )
        RadioOption(
            text = "Author",
            selected = selectedIdentity == Identity.AUTHOR,
            onSelect = { onSelectIdentity(Identity.AUTHOR) }
        )
    }
}

@Composable
fun RadioOption(text: String, selected: Boolean, onSelect: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable(onClick = onSelect)
    ) {
        RadioButton(selected = selected, onClick = onSelect)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}


























//package code.ui.screens.shared
//
//import android.annotation.SuppressLint
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.material3.RadioButton
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.codelab.basiclayouts.R
//import code.model.LoginUser
//import code.model.SelectedIdentity.selectedIdentity
//import code.ui.components.BottomComponent
//import code.ui.components.BottomLoginTextComponent
//import code.ui.components.ForgotPasswordTextComponent
//import code.ui.components.HeadingTextComponent
//import code.ui.components.ImageComponent
//import code.ui.components.MainPageButton
//import code.ui.components.MyTextField
//import code.ui.components.PasswordInputComponent
//
//@Composable
//fun LoginScreen(
//    navController: NavHostController,
//    ) {
//    val selectedIdentity = remember { mutableStateOf(Identity.READER) }
//    LoginContent(
//        navController = navController,
//        selectedIdentity=selectedIdentity,
//        onChangePassword = { LoginUser.password = it },
//        onChangeEmail = { LoginUser.email = it },
//    )
//}
//
//@SuppressLint("UnrememberedMutableState")
//@Composable
//private fun LoginContent(
//    navController: NavHostController,
//    selectedIdentity: MutableState<Identity>,
//    onChangePassword: (String) -> Unit,
//    onChangeEmail: (String) -> Unit,
//) {
//    Surface(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(20.dp),
//        color = Color.White
//    ) {
//        Column {
//            ImageComponent(image = R.drawable.share_sweet_franky)
//            Spacer(modifier = Modifier.height(10.dp))
//            HeadingTextComponent(heading = "Login")
//            Spacer(modifier = Modifier.height(20.dp))
//            Column {
//                MyTextField(
//                    labelVal = "email ID",
//                    R.drawable.share_at_symbol,
//                    onTextChange = onChangeEmail
//                )
//                Spacer(modifier = Modifier.height(15.dp))
//                PasswordInputComponent(labelVal = "Password", onChangePassword)
//                Spacer(modifier = Modifier.height(15.dp))
//                Row(
//                    horizontalArrangement = Arrangement.End,
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    ForgotPasswordTextComponent(navController)
//                }
//                IdentityOptions(
////                    selectedIdentity = mutableStateOf(selectedIdentity.value),
//                    selectedIdentity = selectedIdentity,
//                )
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.TopStart
//                ) {
//                    Column {
//                        MainPageButton(
//                            labelVal = "Continue",
//                            identity = selectedIdentity.value,
//                            navController = navController,
//                            onclick = { }
//                        )
//                        BottomComponent()
//                        Spacer(modifier = Modifier.height(5.dp))
//                        BottomLoginTextComponent(
//                            initialText = "        Haven't we seen you around here before? ",
//                            action = "Join us!",
//                            navController
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//enum class Identity {
//    READER,
//    AUTHOR
//}
//@Composable
//fun IdentityOptions(
//    selectedIdentity: MutableState<Identity>
//) {
//    Column {
//        RadioOption(
//            text = "Reader",
//            selected = selectedIdentity.value == Identity.READER,
//            onSelect = { selectedIdentity.value = Identity.READER }
//        )
//        RadioOption(
//            text = "Author",
//            selected = selectedIdentity.value == Identity.AUTHOR,
//            onSelect = { selectedIdentity.value = Identity.AUTHOR }
//        )
//    }
//}
//
//@Composable
//fun RadioOption(text: String, selected: Boolean, onSelect: () -> Unit) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier.clickable(onClick = onSelect)
//    ) {
//        RadioButton(selected = selected, onClick = onSelect)
//        Spacer(modifier = Modifier.width(8.dp))
//        Text(text = text)
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
//    val navController = rememberNavController()
//    LoginScreen(navController = navController)
//}
