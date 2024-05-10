package com.codelab.basiclayouts.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.basiclayouts.ui.theme.AppTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codelab.basiclayouts.ui.theme.DarkTheme
import com.codelab.basiclayouts.ui.uistate.author.ChapterAU
import com.codelab.basiclayouts.ui.uistate.author.ContentAU
import com.codelab.basiclayouts.ui.uistate.author.OptionAU
import com.codelab.basiclayouts.ui.viewmodel.author.AuthorEditViewModel
import androidx.compose.runtime.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@Composable
fun ChapterTitleSection(chapterTitle: String) {
    AppTheme {
        Box(contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            Text(text = chapterTitle, style = MaterialTheme.typography.displayMedium)
        }
    }
}


@Composable
fun ContentSection(contentList: List<ContentAU>, viewModel: AuthorEditViewModel) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.5f),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            contentList.forEach { content ->
                var text by remember(content.contentData) { mutableStateOf(content.contentData) }
                val keyboardController = LocalSoftwareKeyboardController.current
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextField(
                        value = text,
                        onValueChange = {
                            text = it
                            viewModel.updateContent(content.contentId, it)
                        },
                        label = { Text("Edit Content") },
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                keyboardController?.hide()
                            }
                        ),
                        // 设置 singleLine 为 false，允许多行输入
                        singleLine = false,
                        // 使用 Modifier.weight(1f) 使文本框占据剩余的可用空间
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        onClick = {
                            viewModel.removeContent(content.contentId)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Remove Content"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Button(
                onClick = {
                    viewModel.addNewContent()
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Add Content")
            }
        }
    }
}



//@Composable
//fun ContentItem(content: ContentAU, viewModel: AuthorEditViewModel) {
//    var text by remember { mutableStateOf(content.contentData) }
//    val keyboardController = LocalSoftwareKeyboardController.current
//    Row(verticalAlignment = Alignment.CenterVertically) {
//        TextField(
//            value = text,
//            onValueChange = {
//                text = it
//                viewModel.updateContent(content.contentId, it)
//            },
//            label = { Text("Edit Content") },
//            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
//            keyboardActions = KeyboardActions(
//                onDone = {
//                    keyboardController?.hide()
//                }
//            )
//        )
//        Spacer(modifier = Modifier.width(8.dp))
//        IconButton(
//            onClick = {
//                viewModel.removeContent(content.contentId)
//            }
//        ) {
//            Icon(
//                imageVector = Icons.Default.Close,
//                contentDescription = "Remove Content"
//            )
//        }
//    }
//}
//
//@Composable
//fun ContentSection(contentList: List<ContentAU>, viewModel: AuthorEditViewModel) {
//    Surface(
//        modifier = Modifier
//            .padding(horizontal = 16.dp)
//            .fillMaxWidth()
//            .fillMaxHeight(0.5f),
//        shape = MaterialTheme.shapes.medium,
//        color = MaterialTheme.colorScheme.surfaceVariant
//    ) {
//        Column(
//            modifier = Modifier
//                .verticalScroll(rememberScrollState())
//                .padding(16.dp)
//        ) {
//            contentList.forEach { content ->
//                ContentItem(content, viewModel)
//                Spacer(modifier = Modifier.height(8.dp))
//            }
//            Button(
//                onClick = {
//                    viewModel.addNewContent()
//                },
//                modifier = Modifier.align(Alignment.CenterHorizontally)
//            ) {
//                Text("Add Content")
//            }
//        }
//    }
//}



//@Composable
//fun KeyedContentSection(contentList: List<ContentAU>, viewModel: AuthorEditViewModel) {
//    ContentSection(contentList = contentList, viewModel = viewModel)
//}

@Composable
fun OptionsSection(optionList: List<OptionAU>, viewModel: AuthorEditViewModel) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.75f), // 占屏幕高度的1/4
        shape = MaterialTheme.shapes.medium, // 圆角
        color = MaterialTheme.colorScheme.surfaceVariant // 背景颜色与主题背景不同
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            optionList.forEach { option ->
                Button(
                    onClick = {
                        // 添加点击逻辑，例如导航或触发事件
                    },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                ) {
                    Text(option.optionName, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChapterTitleSection(chapterTitle: String, onChapterTitleChange: (String) -> Unit) {
    AppTheme {
        val keyboardController = LocalSoftwareKeyboardController.current
        Box(contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            TextField(
                value = chapterTitle,
                onValueChange = { onChapterTitleChange(it) },
                label = { Text("Chapter Title") },
                colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    // Hide the keyboard when "Done" action is triggered
                    keyboardController?.hide()
                }),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun AuthorEditScreen(viewModel: AuthorEditViewModel) {
    AppTheme {
        val state by viewModel.authorEditUiState.collectAsState()
        var chapterTitle by remember { mutableStateOf(state.thisChapter.chapterTitle) }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Use the extracted ChapterTitleSection composable
                ChapterTitleSection(chapterTitle) { newChapterTitle ->
                    chapterTitle = newChapterTitle
                    viewModel.updateChapterTitle(chapterTitle)
                }

                Spacer(modifier = Modifier.height(16.dp))
                ContentSection(contentList = state.thisChapter.contentList, viewModel = viewModel)
                Spacer(modifier = Modifier.height(16.dp))
                OptionsSection(optionList = state.thisChapter.optionList, viewModel = viewModel)
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { viewModel.printAuthorEditUiState() },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("Submit", color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    }
}


@Composable
fun AuthorEditMainScreen(viewModel: AuthorEditViewModel = viewModel()) {
    DarkTheme {
        AuthorEditScreen(viewModel)
    }
}

@Preview(showBackground = true)
@Composable
fun AuthorEditMainScreenPreview(){
    AuthorEditMainScreen()
}
