package com.codelab.basiclayouts.ui.screens.reader

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codelab.basiclayouts.R
import com.codelab.basiclayouts.model.reader.readerTStorysForUiState
import com.codelab.basiclayouts.ui.viewmodel.reader.ReaderLibraryScreenViewModel


@Composable
fun ReaderLibraryScreen(viewModel: ReaderLibraryScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val stories by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        LibraryHeader()
        stories.forEach { story ->
            StoryCard(story, viewModel)
        }
    }
}

@Composable
fun StoryCard(story: readerTStorysForUiState, viewModel: ReaderLibraryScreenViewModel) {
    val isFavorited = remember { mutableStateOf(false) }
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), verticalAlignment = Alignment.Top) {
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
                    isFavorited.value = !isFavorited.value
                    viewModel.toggleFavorite(story.storyId)
                }) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Favorite", tint = if (isFavorited.value) androidx.compose.ui.graphics.Color.Red else androidx.compose.ui.graphics.Color.Gray)
                }
                Text("${story.storyName} by ${story.author}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Text(story.storyDescription, fontSize = 14.sp)
            Text("${story.currentProgress}", fontSize = 12.sp, color = androidx.compose.ui.graphics.Color.Gray)
        }
    }
}



@Composable
fun LibraryHeader() {
    Text("Library", fontSize = 24.sp, fontWeight = FontWeight.Bold)
}