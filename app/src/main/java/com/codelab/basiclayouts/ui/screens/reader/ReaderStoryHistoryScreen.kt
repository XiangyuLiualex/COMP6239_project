package com.codelab.basiclayouts.ui.screens.reader

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

import androidx.compose.ui.Modifier
import com.codelab.basiclayouts.model.reader.readingPath
import com.codelab.basiclayouts.ui.viewmodel.reader.ReaderStoryHistoryViewModel

import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.ui.unit.dp

@Composable
fun ReaderStoryHistoryScreen(viewModel: ReaderStoryHistoryViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val historyState = viewModel.historyState.collectAsState().value

    Column(modifier = Modifier.padding(16.dp)) {
        historyState.readingPath.forEach { path ->
            PathCard(path = path)
        }
    }
}

@Composable
fun PathCard(path: readingPath) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), elevation = 4.dp) {
        Column(modifier = Modifier.padding(8.dp)) {
            path.readingPathItemList.forEachIndexed { index, item ->
                val nextChapterName = path.readingPathItemList.getOrNull(index + 1)?.chapterName ?: "结束"
                Text(text = "${item.chapterName} -> $nextChapterName")
            }
        }
    }
}