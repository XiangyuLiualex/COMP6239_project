package com.codelab.basiclayouts.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.codelab.basiclayouts.R
import com.codelab.basiclayouts.ui.screens.shared.Identity
import com.codelab.basiclayouts.ui.theme.BgSocial
import com.codelab.basiclayouts.ui.theme.BorderColor
import com.codelab.basiclayouts.ui.theme.BrandColor
import com.codelab.basiclayouts.ui.theme.Primary
import com.codelab.basiclayouts.ui.theme.Tertirary
import com.codelab.basiclayouts.ui.viewmodel.shared.SignupViewModel

@Composable
fun ImageComponent(image: Int) {
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .size(250.dp)
    )
}

@Composable
fun HeadingTextComponent(heading: String) {
    Text(
        text = heading,
        modifier = Modifier.fillMaxWidth(),
        fontSize = 39.sp,
        color = Primary,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ForgotPasswordHeadingTextComponent(action: String) {
    Column {
        Text(
            text = action,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 39.sp,
            color = Primary,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Password?",
            modifier = Modifier.fillMaxWidth().offset(y = (-18).dp),
            fontSize = 39.sp,
            color = Primary,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun MyTextField(
    labelVal: String,
    icon: Int,
    onTextChange: (String) -> Unit
) {
    var textVal by remember {
        mutableStateOf("")
    }
    val typeOfKeyboard: KeyboardType = when (labelVal) {
        "Username" -> KeyboardType.Text
        "Password" -> KeyboardType.Password
        "Confirm Password" -> KeyboardType.Password
        "email ID" -> KeyboardType.Email
        else -> KeyboardType.Text
    }

    OutlinedTextField(
        value = textVal,
        onValueChange = {
            textVal = it
            onTextChange(it)
        },
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = BrandColor,
            unfocusedBorderColor = BorderColor,
            focusedTextColor = Color.Black,
            focusedLeadingIconColor = BrandColor,
            unfocusedLeadingIconColor = Tertirary
        ),
        shape = MaterialTheme.shapes.small,
        placeholder = {
            Text(text = labelVal, color = Tertirary)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "at_symbol"
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = typeOfKeyboard,
            imeAction = ImeAction.Done
        ),
        singleLine = true
    )
}

enum class Sex {
    MALE,
    FEMALE
}
@Composable
fun SexOptions(
    selectedSex: MutableState<Sex>
) {
    Column (
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()
    ){

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedSex.value == Sex.MALE,
                onClick = { selectedSex.value = Sex.MALE },
            )
            Text(
                text = "Male",
                color = Tertirary,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(end = 16.dp)
            )
            RadioButton(
                selected = selectedSex.value == Sex.FEMALE,
                onClick = { selectedSex.value = Sex.FEMALE }
            )
            Text(
                text = "Female",
                color = Tertirary,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}

@Composable
fun SexOptionsTextField(
    labelVal: String,
    icon: Int,
) {
    OutlinedTextField(
        value = "", // Empty value to hide the input field
        onValueChange = { },
        Modifier.fillMaxWidth(0.3f),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = BrandColor,
            unfocusedBorderColor = BorderColor,
            focusedTextColor = Color.Black,
            focusedLeadingIconColor = BrandColor,
            unfocusedLeadingIconColor = Tertirary
        ),
        shape = MaterialTheme.shapes.small,
        placeholder = {
            Text(text = labelVal, color = Tertirary)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "at_symbol"
            )
        },
        singleLine = true
    )
}

@Composable
fun PasswordInputComponent(
    labelVal: String,
    onChangePassword: (String) -> Unit
) {
    var password by remember {
        mutableStateOf("")
    }
    var isShowPassword by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = password,
        onValueChange = {
            password = it
            onChangePassword(it)
        },
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = BrandColor,
            unfocusedBorderColor = BorderColor,
            focusedTextColor = Color.Black
        ),
        shape = MaterialTheme.shapes.small,
        placeholder = {
            Text(text = labelVal, color = Tertirary)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.share_lock),
                contentDescription = "at_symbol",
                tint = Tertirary
            )
        },
        trailingIcon = {
            val description = if (isShowPassword) "Show Password" else "Hide Password"
            val iconImage =
                if (isShowPassword) R.drawable.share_pheyeclosedfill__1_ else R.drawable.share_eye_closed
            IconButton(onClick = {
                isShowPassword = !isShowPassword
            }) {
                Icon(
                    painter = painterResource(id = iconImage),
                    contentDescription = description,
                    tint = Tertirary,
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun PasswordConfirmComponent(
    labelVal: String,
    password: String,
    onPasswordChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = password,
        onValueChange = { onPasswordChange(it) },
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = BrandColor,
            unfocusedBorderColor = BorderColor,
            focusedTextColor = Color.Black
        ),
        shape = MaterialTheme.shapes.small,
        placeholder = {
            Text(text = labelVal, color = Tertirary)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.share_lock),
                contentDescription = "lock_icon",
                tint = Tertirary
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation()
    )
}


@Composable
fun ForgotPasswordTextComponent(navController: NavHostController) {
    Text(
        text = "Forgot Password?",
        color = BrandColor,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier.clickable {
            navController.navigate("ForgotPassword")
        }
    )
}

@Composable
fun MyButton(labelVal: String, navController: NavHostController) {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(
            containerColor = BrandColor
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = labelVal,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.clickable {
                if (labelVal == "Submit") {
                    navController.navigate("ResetPassword")
                }
            }
        )
    }
}

@Composable
fun ConfirmButton(
        labelVal: String,
        navController: NavHostController,
        signupViewModel: SignupViewModel,
        onClick: () -> Unit,
) {
    Button(
        onClick = {
            if (labelVal == "Save") {
                if (signupViewModel.state.value.password == signupViewModel.state.value.confirmPassword) {
                    navController.navigate("LoginScreen")
                }else {
                    //"Confirm password is wrong"
                }
            }else if (labelVal == "Submit") {
                if (signupViewModel.state.value.password == signupViewModel.state.value.confirmPassword) {
                    navController.navigate("LoginScreen")
                }else {
                    //"Confirm password is wrong"
                }
            }
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = BrandColor
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = labelVal,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.clickable { }
        )
    }
}

@Composable
fun MainPageButton(labelVal: String,
                   identity: Identity,
                   navController: NavHostController,
                   onclick:()->Unit
) {
    Button(
        onClick = {
            onclick()
            when (identity) {
                Identity.READER -> navController.navigate("GuestScreen")
                Identity.AUTHOR -> navController.navigate("author_home_Screen")
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = BrandColor
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = labelVal,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.clickable { }
        )
    }
}

@Composable
fun BottomComponent() {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                thickness = 1.dp,
                color = Tertirary
            )
            Text(
                text = "OR",
                color = Tertirary,
                fontSize = 20.sp
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                thickness = 1.dp,
                color = Tertirary
            )
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = BgSocial
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.share_google),
                        contentDescription = "google icon"
                    )
                    Text(
                        text = "Login With Google",
                        fontSize = 18.sp,
                        color = Tertirary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
            }
        }
    }
}

@Composable
fun BottomLoginTextComponent(
    initialText: String,
    action: String,
    navController: NavHostController
) {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Tertirary)) {
            append(initialText)
        }
        withStyle(style = SpanStyle(color = BrandColor, fontWeight = FontWeight.Bold)) {
            pushStringAnnotation(tag = action, annotation = action)
            append(action)
        }
    }

    ClickableText(text = annotatedString, onClick = {
        annotatedString.getStringAnnotations(it, it)
            .firstOrNull()?.also { span ->
                Log.d("BottomLoginTextComponent", "${span.item} is Clicked")
                if (span.item == "Join us!") {
                    navController.navigate("SignupScreen")
                }
            }
    })
}

@Composable
fun SignupTermsAndPrivacyText() {
    val initialText = "Join us and accept our "
    val termsNConditionText = "Terms & Conditions"
    val andText = " and "
    val privacyPolicyText = "Privacy Policy."
    val lastText = " Don't be shy, just show yourself!"

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Tertirary)) {
            append(initialText)
        }
        withStyle(style = SpanStyle(color = BrandColor, fontWeight = FontWeight.Bold)) {
            pushStringAnnotation(tag = termsNConditionText, annotation = termsNConditionText)
            append(termsNConditionText)
        }
        withStyle(style = SpanStyle(color = Tertirary)) {
            append(andText)
        }
        withStyle(style = SpanStyle(color = BrandColor, fontWeight = FontWeight.Bold)) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        withStyle(style = SpanStyle(color = Tertirary)) {
            append(lastText)
        }
    }

    ClickableText(text = annotatedString, onClick = {
        annotatedString.getStringAnnotations(it, it)
            .firstOrNull()?.also { span ->
                Log.d("SignupTermsAndPrivacyText", span.item)
            }
    })
}

@Composable
fun BottomSignupTextComponent(navController: NavHostController) {
    val initialText = "  Are you a beautiful soul? "
    val loginText = "Log In"
    val lastText = " again and join our party!"

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Tertirary)) {
            append(initialText)
        }
        withStyle(style = SpanStyle(color = BrandColor, fontWeight = FontWeight.Bold)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
        withStyle(style = SpanStyle(color = Tertirary)) {
            append(lastText)
        }
    }

    ClickableText(text = annotatedString, onClick = {
        annotatedString.getStringAnnotations(it, it)
            .firstOrNull()?.also { span ->
                if (span.item == "Log In") {
                    navController.navigate("LoginScreen")
                }
            }
    })

}

@Composable
fun TextInfoComponent(textVal: String) {
    Text(text = textVal, color = Tertirary)
}