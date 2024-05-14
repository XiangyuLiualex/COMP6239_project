package com.codelab.basiclayouts.ui.screens.reader

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.codelab.basiclayouts.R
import com.codelab.basiclayouts.model.reader.readerTStorysForUiState
import com.codelab.basiclayouts.ui.uistate.reader.ReaderLibraryScreenUiState
import com.codelab.basiclayouts.ui.viewmodel.reader.ReaderLibraryScreenViewModel

@Composable
fun ReaderLibraryScreen(
    viewModel: ReaderLibraryScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController
) {
    val state = viewModel.uiState.collectAsState().value

    Column(modifier = Modifier.padding(16.dp)) {
        LibraryHeader()
        state.readerTStorys.forEach { story ->
            StoryCard(story, viewModel, navController)
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
                navController.navigate("storyContent/${story.storyId}/${story.currentChapterId}")
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
