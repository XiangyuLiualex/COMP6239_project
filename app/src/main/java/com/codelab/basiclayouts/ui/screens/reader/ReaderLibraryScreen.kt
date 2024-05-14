package com.codelab.basiclayouts.ui.screens.reader

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.codelab.basiclayouts.R
import com.codelab.basiclayouts.model.reader.readerTStorysForUiState
import com.codelab.basiclayouts.ui.screens.shared.GuestFavorate
import com.codelab.basiclayouts.ui.screens.shared.GuestLibrary
import com.codelab.basiclayouts.ui.screens.shared.GuestMain
import com.codelab.basiclayouts.ui.screens.shared.GuestProfile
import com.codelab.basiclayouts.ui.theme.Primary
import com.codelab.basiclayouts.ui.uistate.reader.ReaderLibraryScreenUiState
import com.codelab.basiclayouts.ui.viewmodel.reader.ReaderLibraryScreenViewModel

@Composable
fun ReaderLibraryScreen(    navController: NavHostController,
    viewModel: ReaderLibraryScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

) {
    val state = viewModel.uiState.collectAsState().value

    Column(modifier = Modifier.padding(16.dp)) {
        LibraryHeader()
        state.readerTStorys.forEach { story ->
            StoryCard(story, viewModel, navController)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewModel.loadStories(R.integer.READERID) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Refresh")
        }
    }
}

@Composable
fun StoryCard(story: readerTStorysForUiState, viewModel: ReaderLibraryScreenViewModel, navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                val readerId = viewModel.uiState.value.readerId ?: 0
                val storyId = story.storyId ?: 0
                val chapterId = story.currentChapterId ?: 0
                val currentReadingPathId = story.currentReadingPathId ?: 0
                navController.navigate("storyContent/$readerId/$storyId/$chapterId/$currentReadingPathId")
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    viewModel.toggleFavorite(story.storyId, viewModel.uiState.value.readerId)
                }) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Unfavorite", tint = androidx.compose.ui.graphics.Color.Red)
                }
                Text("${story.storyName}  ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text("Trends:${story.storyTrends}", fontWeight = FontWeight.Thin, fontSize = 13.sp)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("${story.author}", fontSize = 14.sp)
                Text(":${story.storyDescription}", fontSize = 14.sp)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("${story.currentProgressText}:", fontSize = 14.sp)
                Text(":${story.currentChapterName}", fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun LibraryHeader() {
    Text("Library", fontSize = 24.sp, fontWeight = FontWeight.Bold)
}


@Composable
private fun BottomBarForLib (pageIndex: MutableState<PageItemForLib>) {
    NavigationBar () {
        NavigationBarItem(
            selected = pageIndex.value == PageItemForLib.MAIN_PAGE,
            onClick = {
                pageIndex.value = PageItemForLib.MAIN_PAGE
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
            selected = pageIndex.value == PageItemForLib.FAVORITE_PAGE,
            onClick = {
                pageIndex.value = PageItemForLib.FAVORITE_PAGE
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
            selected = pageIndex.value == PageItemForLib.LIBRARY_PAGE,
            onClick = {
                pageIndex.value = PageItemForLib.LIBRARY_PAGE
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
            selected = pageIndex.value == PageItemForLib.PROFILE_PAGE,
            onClick = {
                pageIndex.value = PageItemForLib.PROFILE_PAGE
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

private enum class PageItemForLib {
    MAIN_PAGE,
    FAVORITE_PAGE,
    LIBRARY_PAGE,
    PROFILE_PAGE,
}



@Composable
fun LibScreen (nav: NavHostController) {
    val pageIndex = remember {
        mutableStateOf(PageItemForLib.MAIN_PAGE)
    }

    androidx.compose.material3.Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
    ) {
        androidx.compose.material3.Scaffold(
            bottomBar = { BottomBarForLib(pageIndex) },
        ) { _ ->
            when (pageIndex.value) {
                PageItemForLib.MAIN_PAGE -> StoryHomeScreen(nav)
                PageItemForLib.FAVORITE_PAGE -> ReaderFavouriteScreen(nav)
                PageItemForLib.LIBRARY_PAGE -> ReaderLibraryScreen(nav)
                PageItemForLib.PROFILE_PAGE -> GuestProfile(nav)
            }
        }
    }
}