package com.codelab.basiclayouts.ui.screens.author

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codelab.basiclayouts.ui.screen.AuthorEditMainScreen
import com.codelab.basiclayouts.ui.screen.StoryEditScreen
import com.codelab.basiclayouts.ui.viewmodel.author.AuthorEditViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codelab.basiclayouts.R
import com.codelab.basiclayouts.ui.screens.shared.GuestFavorate
import com.codelab.basiclayouts.ui.screens.shared.GuestLibrary
import com.codelab.basiclayouts.ui.screens.shared.GuestMain
import com.codelab.basiclayouts.ui.screens.shared.GuestProfile

import com.codelab.basiclayouts.ui.theme.Primary

@Composable
fun ParentScreen(navController: NavHostController,viewModel: AuthorEditViewModel = viewModel(),) {
    val activeScreen by viewModel.activeScreen.collectAsState()

//    navController.navigate("MainScreen")

    when (activeScreen) {
        "AuthorMainScreen" ->AuthorMainScreen(viewModel,navController)
        "StoryStatisticsScreen" -> StoryStatisticsScreen(viewModel)
        "NewStoryScreen" -> NewStoryScreen(viewModel)
        "StoryEditScreen" -> StoryEditScreen(viewModel)
        "AuthorEditMainScreen" -> AuthorEditMainScreen(viewModel)
    }

}

private enum class APageItem {
    MAIN_PAGE,
    PROFILE_PAGE,
}

@Composable
private fun ABottomBar (pageIndex: MutableState<APageItem>) {
    NavigationBar () {
        NavigationBarItem(
            selected = pageIndex.value == APageItem.MAIN_PAGE,
            onClick = {
                pageIndex.value = APageItem.MAIN_PAGE
            },
            icon = { Image(
                painter = painterResource(id = R.drawable.share_franky),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            ) },
            label = { Text(
                text = "main",
                fontSize = 15.sp,
                color = Primary,
                fontWeight = FontWeight.Bold
            ) }
        )

        NavigationBarItem(
            selected = pageIndex.value == APageItem.PROFILE_PAGE,
            onClick = {
                pageIndex.value = APageItem.PROFILE_PAGE
            },
            icon = { Image(
                painter = painterResource(id = R.drawable.share_lantern),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            ) },
            label = { Text(
                text = "profile",
                fontSize = 15.sp,
                color = Primary,
                fontWeight = FontWeight.Bold
            ) }
        )
    }
}

@Composable
fun AuthorScreen (nav: NavHostController) {
    val pageIndex = remember {
        mutableStateOf(APageItem.MAIN_PAGE)
    }
    Surface (
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold (
            bottomBar = { ABottomBar(pageIndex) },
        ) { _ ->
            when (pageIndex.value) {
                APageItem.MAIN_PAGE     -> ParentScreen(nav)
                APageItem.PROFILE_PAGE  -> GuestProfile(nav)
            }
        }
    }
}

@Preview
@Composable
fun AuthorScreenPrievew(){
    val navController = rememberNavController()
    AuthorScreen(navController)
}



