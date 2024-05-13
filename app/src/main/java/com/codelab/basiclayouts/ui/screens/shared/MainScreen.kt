package com.codelab.basiclayouts.ui.screens.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codelab.basiclayouts.ui.components.ImageComponent
import com.codelab.basiclayouts.R
import com.codelab.basiclayouts.ui.theme.Primary

@Composable
fun MainScreen (navController: NavHostController) {
    Surface (
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image (
            painter = painterResource(id = R.drawable.share_main_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(50.dp))
            ImageComponent(image = R.drawable.share_spooktacular_ghost)
            Spacer(modifier = Modifier.height(50.dp))
            MainButton(text = "Sign In") {
                navController.navigate("LoginScreen")
            }
            Spacer(modifier = Modifier.height(30.dp))
            MainButton(text = "Sign Up") {
                navController.navigate("SignupScreen")
            }
            Spacer(modifier = Modifier.height(30.dp))
            MainButton(text = "Guest") {
                navController.navigate("GuestScreen")
            }
        }
    }
}

@Composable
fun MainButton (text: String, onclick: () -> Unit) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ){
        Button(
            onClick = onclick,
            modifier = Modifier.fillMaxWidth(0.75f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary
            )
        ) {
            Text (
                text = text,
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.focusable(false)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview () {
    val navController = rememberNavController()
    MainScreen(navController)
}