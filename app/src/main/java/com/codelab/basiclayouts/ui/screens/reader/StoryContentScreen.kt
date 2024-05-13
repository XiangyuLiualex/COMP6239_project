package com.codelab.basiclayouts.ui.screens.reader
import android.widget.VideoView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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



import androidx.lifecycle.viewmodel.compose.viewModel
import com.codelab.basiclayouts.model.reader.readerTContent
import com.codelab.basiclayouts.model.reader.readerTOption
import com.codelab.basiclayouts.ui.viewmodel.reader.ReaderLibraryScreenViewModel
import com.codelab.basiclayouts.ui.viewmodel.reader.StoryContentScreenViewModel

@Composable
fun StoryContentScreen(viewModel: StoryContentScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val state = viewModel.uiState.collectAsState().value

    Column(modifier = Modifier.padding(16.dp)) {
        StoryHeader(state.readerTStorys.storyName, state.readerTChapter.chapterTitle)
        StoryContent(state.readerTContentList.map {
            convertToContentItem(it)
        })
        ChapterOptions(state.readerTOptionList.map { it },viewModel)
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
fun ChapterOptions(options: List<readerTOption>, viewModel:StoryContentScreenViewModel) {
    options.forEach { option ->
        Button(onClick = { viewModel.loadNextChapterByOption(1,option.nextChapterId) }, modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
            Text(text = option.optionName)
        }
    }
}

fun convertToContentItem(readerTContent:  readerTContent): ContentItem {
    return when (readerTContent.contentType) {
        0 -> ContentItem.Text(readerTContent.contentData)
        1 -> ContentItem.Image(R.drawable.ab2_quick_yoga) // 根据情况替换readerStoryContent.contentData
        2 -> ContentItem.Video(readerTContent.contentData)
        else -> throw IllegalArgumentException("Unsupported content type")
    }
}

//定义内容类型 Define content types
sealed class ContentItem {
    data class Text(val text: String) : ContentItem()
    data class Image(val resourceId: Int) : ContentItem()
    data class Video(val videoUrl: String) : ContentItem()
}
