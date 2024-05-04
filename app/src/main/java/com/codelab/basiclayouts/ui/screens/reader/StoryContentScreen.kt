package com.codelab.basiclayouts.ui.screens.reader
import android.widget.VideoView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.codelab.basiclayouts.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@Composable
fun StoryContentScreen(storyTitle: String, chapterTitle: String, contentItems: List<ContentItem>, options: List<String>) {
    Column(modifier = Modifier.padding(16.dp)) {
        StoryHeader(storyTitle, chapterTitle)
        StoryContent(contentItems)
        ChapterOptions(options)
    }
}

@Composable
fun StoryHeader(storyTitle: String, chapterTitle: String) {
    Column {
        Text(text = storyTitle, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = chapterTitle, fontSize = 18.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun StoryContent(contentItems: List<ContentItem>) {
    contentItems.forEach { item ->
        when (item) {
            is ContentItem.Text -> Text(text = item.text, fontSize = 16.sp)
            is ContentItem.Image -> Image(painter = painterResource(id = item.resourceId), contentDescription = null, modifier = Modifier.fillMaxWidth().height(200.dp), contentScale = ContentScale.Crop)
            is ContentItem.Video -> AndroidView(factory = { VideoView(it).apply { setVideoPath(item.videoUrl); start() } })
        }
    }
}

@Composable
fun ChapterOptions(options: List<String>) {
    options.forEach { option ->
        Button(onClick = { /* Handle click */ }, modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
            Text(text = option)
        }
    }
}

// Define content types
sealed class ContentItem {
    data class Text(val text: String) : ContentItem()
    data class Image(val resourceId: Int) : ContentItem()
    data class Video(val videoUrl: String) : ContentItem()
}

@Preview(showBackground = true)
@Composable
fun PreviewStoryPage() {
    MaterialTheme {
        StoryContentScreen(
            storyTitle = "The Adventure Begins",
            chapterTitle = "Chapter 1: The Awakening",
            contentItems = listOf(
                ContentItem.Text("It was a dark and stormy night...\nIt was a dark and stormy night...\nIt was a dark and stormy night...\n"),
                ContentItem.Image(R.drawable.ab2_quick_yoga),
                ContentItem.Video("path/to/video")
            ),
            options = listOf("Go to the castle", "Return home")
        )
    }
}
