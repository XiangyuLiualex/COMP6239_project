package com.codelab.basiclayouts.ui.screens.reader

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codelab.basiclayouts.R
import com.codelab.basiclayouts.model.Story

data class ReaderLibrary(
    val title: String,
    val author: String,
    val imageUrl: String,
    val description: String,
    val progress: String,
    val currentChapter: String
)

@Composable
fun StoryList(stories: List<Story>) {
    Column(modifier = Modifier.padding(16.dp)) {
        LibraryHeader()
        stories.forEach { story ->
            StoryCard(story)
        }
    }
}

@Composable
fun LibraryHeader() {
    Text("Library", fontSize = 24.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun StoryCard(story: Story) {
    val isFavorited = remember { mutableStateOf(false) }
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), verticalAlignment = Alignment.Top) {
        Image(
            painter = painterResource(id = R.drawable.ab2_quick_yoga), // Replace with actual image resource
            contentDescription = "Story Cover",
            modifier = Modifier.size(100.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f).padding(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { isFavorited.value = !isFavorited.value }) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Favorite", tint = if (isFavorited.value) androidx.compose.ui.graphics.Color.Red else androidx.compose.ui.graphics.Color.Gray)
                }
                Text("${story.title} by ${story.author}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Text(story.description, fontSize = 14.sp)
            Text("${story.progress} - ${story.currentChapter}", fontSize = 12.sp, color = androidx.compose.ui.graphics.Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStoryList() {
    MaterialTheme {
        StoryList(
            listOf(
                Story("The Adventure Begins", "John Doe", "url_to_image", "An exciting start to an epic adventure.", "Chapter 5", "The Battle of the East"),
                Story("Lost in Time", "Jane Smith", "url_to_image", "A journey through time and space.", "Chapter 2", "Arrival in the Past")
            )
        )
    }
}
