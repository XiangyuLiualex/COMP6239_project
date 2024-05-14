package com.codelab.basiclayouts.ui.screens.reader

import android.widget.VideoView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.codelab.basiclayouts.R
import com.codelab.basiclayouts.model.reader.readerTContent
import com.codelab.basiclayouts.model.reader.readerTOption
import com.codelab.basiclayouts.model.reader.readerTStorysForUiState
import com.codelab.basiclayouts.ui.screens.shared.GuestMain
import com.codelab.basiclayouts.ui.screens.shared.GuestProfile
import com.codelab.basiclayouts.ui.theme.Primary
import com.codelab.basiclayouts.ui.viewmodel.reader.StoryContentScreenViewModel
import com.codelab.basiclayouts.ui.viewmodel.reader.StoryHomeScreenViewModel

@Composable
fun StoryHomeScreen(
    navController: NavHostController,
    viewModel: StoryHomeScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val state = viewModel.uiState.collectAsState().value

    // 每次进入页面时重新加载数据
    LaunchedEffect(Unit) {
        viewModel.loadStories()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        HomeHeader()
        state.readerTStorys.forEach { story ->
            StoryHomeCard(story, navController)
        }
    }
}

@Composable
fun StoryHomeCard(story: readerTStorysForUiState, navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                val tempid = R.integer.READERID
                navController.navigate("storyContent/$tempid/${story.storyId}/0/0")
            },
        verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.ab2_quick_yoga),
            contentDescription = "Story Cover",
            modifier = Modifier.size(100.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f).padding(8.dp)) {
            Text("${story.storyName}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text("${story.storyDescription}", fontSize = 14.sp)
        }
    }
}

@Composable
fun HomeHeader() {
    Text("Story Home", fontSize = 24.sp, fontWeight = FontWeight.Bold)
}





@Composable
private fun BottomBarForHome (pageIndex: MutableState<PageItemForHome>) {
    NavigationBar () {
        NavigationBarItem(
            selected = pageIndex.value == PageItemForHome.MAIN_PAGE,
            onClick = {
                pageIndex.value = PageItemForHome.MAIN_PAGE
            },
            icon = { Image(
                painter = painterResource(id = R.drawable.share_franky),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            ) },
            label = {
                androidx.compose.material3.Text(
                    text = "main",
                    fontSize = 15.sp,
                    color = Primary,
                    fontWeight = FontWeight.Bold
                )
            }
        )
        NavigationBarItem(
            selected = pageIndex.value == PageItemForHome.FAVORITE_PAGE,
            onClick = {
                pageIndex.value = PageItemForHome.FAVORITE_PAGE
            },
            icon = { Image(
                painter = painterResource(id = R.drawable.share_owl),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            ) },
            label = {
                androidx.compose.material3.Text(
                    text = "favorite",
                    fontSize = 15.sp,
                    color = Primary,
                    fontWeight = FontWeight.Bold
                )
            }
        )
        NavigationBarItem(
            selected = pageIndex.value == PageItemForHome.LIBRARY_PAGE,
            onClick = {
                pageIndex.value = PageItemForHome.LIBRARY_PAGE
            },
            icon = { Image(
                painter = painterResource(id = R.drawable.share_bone),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            ) },
            label = {
                androidx.compose.material3.Text(
                    text = "library",
                    fontSize = 15.sp,
                    color = Primary,
                    fontWeight = FontWeight.Bold
                )
            }
        )
        NavigationBarItem(
            selected = pageIndex.value == PageItemForHome.PROFILE_PAGE,
            onClick = {
                pageIndex.value = PageItemForHome.PROFILE_PAGE
            },
            icon = { Image(
                painter = painterResource(id = R.drawable.share_lantern),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            ) },
            label = {
                androidx.compose.material3.Text(
                    text = "profile",
                    fontSize = 15.sp,
                    color = Primary,
                    fontWeight = FontWeight.Bold
                )
            }
        )
    }
}

private enum class PageItemForHome {
    MAIN_PAGE,
    FAVORITE_PAGE,
    LIBRARY_PAGE,
    PROFILE_PAGE,
}



@Composable
fun HomeScreen (nav: NavHostController) {
    val pageIndex = remember {
        mutableStateOf(PageItemForHome.MAIN_PAGE)
    }

    androidx.compose.material3.Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
    ) {
        androidx.compose.material3.Scaffold(
            bottomBar = { BottomBarForHome(pageIndex) },
        ) { _ ->
            when (pageIndex.value) {
                PageItemForHome.MAIN_PAGE -> StoryHomeScreen(nav)
                PageItemForHome.FAVORITE_PAGE -> ReaderFavouriteScreen(nav)
                PageItemForHome.LIBRARY_PAGE -> ReaderLibraryScreen(nav)
                PageItemForHome.PROFILE_PAGE -> GuestProfile(nav)
            }
        }
    }
}