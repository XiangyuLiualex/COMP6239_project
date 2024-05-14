//package com.codelab.basiclayouts.ui.screens.reader
//
//import android.widget.VideoView
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.text.BasicText
//import androidx.compose.material.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.viewinterop.AndroidView
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavHostController
//import com.codelab.basiclayouts.R
//import com.codelab.basiclayouts.model.reader.readerTContent
//import com.codelab.basiclayouts.model.reader.readerTOption
//import com.codelab.basiclayouts.ui.viewmodel.reader.StoryContentScreenViewModel
//
//@Composable
//fun StoryContentScreen(
//    viewModel: StoryContentScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
//    readerId: Int,
//    storyId: Int,
//    chapterId: Int,
//    currentReadingPathId: Int,
//    navController: NavHostController
//) {
//    val state = viewModel.uiState.collectAsState().value
//
//    LaunchedEffect(key1 = storyId, key2 = chapterId, key3 = currentReadingPathId) {
//        viewModel.loadContent(readerId, storyId, chapterId, currentReadingPathId)
//    }
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            StoryHeader(state.readerTStorys.storyName, state.readerTChapter.chapterTitle)
//            StoryHeaderButtons(viewModel, navController, readerId, storyId)
//        }
//        StoryContent(state.readerTContentList.map {
//            convertToContentItem(it)
//        })
//        ChapterOptions(state.readerTOptionList.map { it }, viewModel)
//    }
//}
//
//@Composable
//fun StoryHeader(storyTitle: String, chapterTitle: String) {
//    Column {
//        Text(text = storyTitle, fontSize = 24.sp, fontWeight = FontWeight.Bold)
//        Text(text = chapterTitle, fontSize = 18.sp, fontWeight = FontWeight.Medium)
//    }
//}
//
//@Composable
//fun StoryHeaderButtons(viewModel: StoryContentScreenViewModel, navController: NavHostController, readerId: Int, storyId: Int) {
//    Row {
//        Button(onClick = { viewModel.addFavoriteAuthor() }) {
//            Text("Add Favorite Author")
//        }
//        Spacer(modifier = Modifier.width(8.dp))
//        Button(onClick = { viewModel.addToLibrary() }) {
//            Text("Add to Library")
//        }
//        Spacer(modifier = Modifier.width(8.dp))
//        Button(onClick = {
//            navController.navigate("readerStoryHistory/$readerId/$storyId")
//        }) {
//            Text("View History")
//        }
//    }
//}
//
//
//@Composable
//fun StoryContent(contentItems: List<ContentItem>) {
//    contentItems.forEach { item ->
//        when (item) {
//            is ContentItem.Text -> Text(text = item.text, fontSize = 16.sp)
//            is ContentItem.Image -> Image(painter = painterResource(id = item.resourceId), contentDescription = null, modifier = Modifier.fillMaxWidth().height(200.dp), contentScale = ContentScale.Crop)
//            is ContentItem.Video -> AndroidView(factory = { VideoView(it).apply { setVideoPath(item.videoUrl); start() } })
//        }
//    }
//}
//
//@Composable
//fun ChapterOptions(options: List<readerTOption>, viewModel: StoryContentScreenViewModel) {
//    options.forEach { option ->
//        Button(onClick = { viewModel.loadNextChapterByOption(viewModel.uiState.value.storyId, option.nextChapterId) }, modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
//            Text(text = option.optionName)
//        }
//    }
//}
//
//fun convertToContentItem(readerTContent: readerTContent): ContentItem {
//    return when (readerTContent.contentType) {
//        0 -> ContentItem.Text(readerTContent.contentData)
//        1 -> ContentItem.Image(R.drawable.ab2_quick_yoga)
//        2 -> ContentItem.Video(readerTContent.contentData)
//        else -> throw IllegalArgumentException("Unsupported content type")
//    }
//}
//
//// 定义内容类型
//sealed class ContentItem {
//    data class Text(val text: String) : ContentItem()
//    data class Image(val resourceId: Int) : ContentItem()
//    data class Video(val videoUrl: String) : ContentItem()
//}
package com.codelab.basiclayouts.ui.screens.reader

import android.widget.VideoView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.codelab.basiclayouts.ui.viewmodel.reader.StoryContentScreenViewModel

@Composable
fun StoryContentScreen(
    viewModel: StoryContentScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    readerId: Int,
    storyId: Int,
    chapterId: Int,
    currentReadingPathId: Int,
    navController: NavHostController
) {
    val state = viewModel.uiState.collectAsState().value

    LaunchedEffect(key1 = storyId, key2 = chapterId, key3 = currentReadingPathId) {
        viewModel.loadContent(readerId, storyId, chapterId, currentReadingPathId)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StoryHeader(state.readerTStorys.storyName, state.readerTChapter.chapterTitle)
            }
            Spacer(modifier = Modifier.height(8.dp))
            StoryHeaderButtons(viewModel, navController, readerId, storyId)
            Spacer(modifier = Modifier.height(16.dp))
            StoryContent(state.readerTContentList.map {
                convertToContentItem(it)
            })
            Spacer(modifier = Modifier.height(16.dp))
            ChapterOptions(state.readerTOptionList.map { it }, viewModel)
        }
    }
}

@Composable
fun StoryHeader(storyTitle: String, chapterTitle: String) {
    Column {
        Text(
            text = storyTitle,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = chapterTitle,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun StoryHeaderButtons(viewModel: StoryContentScreenViewModel, navController: NavHostController, readerId: Int, storyId: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = { viewModel.addFavoriteAuthor() }) {
            Text("Add Favorite Author")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = { viewModel.addToLibrary() }) {
            Text("Add to Library")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = {
            navController.navigate("readerStoryHistory/$readerId/$storyId")
        }) {
            Text("View History")
        }
    }
}

@Composable
fun StoryContent(contentItems: List<ContentItem>) {
    Column {
        contentItems.forEach { item ->
            when (item) {
                is ContentItem.Text -> Text(text = item.text, fontSize = 16.sp)
                is ContentItem.Image -> Image(
                    painter = painterResource(id = item.resourceId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                is ContentItem.Video -> AndroidView(factory = { VideoView(it).apply { setVideoPath(item.videoUrl); start() } })
            }
        }
    }
}

@Composable
fun ChapterOptions(options: List<readerTOption>, viewModel: StoryContentScreenViewModel) {
    Column {
        options.forEach { option ->
            Button(
                onClick = { viewModel.loadNextChapterByOption(viewModel.uiState.value.storyId, option.nextChapterId) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text(text = option.optionName)
            }
        }
    }
}

fun convertToContentItem(readerTContent: readerTContent): ContentItem {
    return when (readerTContent.contentType) {
        0 -> ContentItem.Text(readerTContent.contentData)
        1 -> ContentItem.Image(R.drawable.ab2_quick_yoga)
        2 -> ContentItem.Video(readerTContent.contentData)
        else -> throw IllegalArgumentException("Unsupported content type")
    }
}

// 定义内容类型
sealed class ContentItem {
    data class Text(val text: String) : ContentItem()
    data class Image(val resourceId: Int) : ContentItem()
    data class Video(val videoUrl: String) : ContentItem()
}

