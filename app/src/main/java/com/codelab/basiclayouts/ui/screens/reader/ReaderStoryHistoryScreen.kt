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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
//考虑到读完书后跳转到这个页面（根据进入的头节点处理结果？）
// 或者专门做一个跳转，从书的详情跳过来
// 还差读书页添加历史记录
@Composable
fun ReaderStoryHistoryScreen(
    viewModel: ReaderStoryHistoryViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    readerId: Int,
    storyId: Int
) {
    LaunchedEffect(key1 = readerId, key2 = storyId) {
        viewModel.loadHistory(readerId, storyId)
    }

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
                val nextChapterName = path.readingPathItemList.getOrNull(index + 1)?.chapterName ?: "end"
                Text(text = "${item.chapterName} -> $nextChapterName")
            }
        }
    }
}