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
import androidx.compose.ui.window.Dialog
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.filled.ArrowBack






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


@Composable
fun OptionItem(option: OptionAU, onRemoveOption: (Int) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Button(
            onClick = { /* Add your logic here */ },
            modifier = Modifier.weight(1f)
        ) {
            Text(option.optionName, style = MaterialTheme.typography.bodyLarge)
        }
        IconButton(
            onClick = { onRemoveOption(option.optionId) }
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Remove Option"
            )
        }
    }
}

//@Composable
//fun OptionsSection(optionList: List<OptionAU>, viewModel: AuthorEditViewModel) {
//    Surface(
//        modifier = Modifier
//            .padding(horizontal = 16.dp)
//            .fillMaxWidth()
//            .fillMaxHeight(0.75f), // 占屏幕高度的1/4
//        shape = MaterialTheme.shapes.medium, // 圆角
//        color = MaterialTheme.colorScheme.surfaceVariant // 背景颜色与主题背景不同
//    ) {
//        Column(
//            modifier = Modifier
//                .verticalScroll(rememberScrollState())
//                .padding(16.dp)
//        ) {
//            optionList.forEach { option ->
//                OptionItem(option) { optionId ->
//                    viewModel.removeOption(optionId)
//                }
//            }
//        }
//    }
//}

@Composable
fun OptionsSection(optionList: List<OptionAU>, viewModel: AuthorEditViewModel) {
    val showDialog = remember { mutableStateOf(false) }
    val showDropdown = remember { mutableStateOf(false) }
    val newOptionName = remember { mutableStateOf("") }
    val selectedChapterId = remember { mutableStateOf<Int?>(null) }
    val selectedChapterTitle = remember { mutableStateOf<String?>(null) }
    val availableChapters = viewModel.authorEditUiState.collectAsState().value.thisStory.chapterList.filter {
        it.chapterId != viewModel.authorEditUiState.collectAsState().value.thisChapter.chapterId
    }

    Surface(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.75f),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            optionList.forEach { option ->
                OptionItem(option) { optionId ->
                    viewModel.removeOption(optionId)
                }
            }
            Button(
                onClick = { showDialog.value = true },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Add Option")
            }
        }
    }

    if (showDialog.value) {
        Dialog(onDismissRequest = { showDialog.value = false }) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextField(
                        value = newOptionName.value,
                        onValueChange = { newOptionName.value = it },
                        label = { Text("Option Name") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Button(
                            onClick = { showDropdown.value = true },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(if (selectedChapterTitle.value != null) "Chosen next Chapter: ${selectedChapterTitle.value}" else "Choose Next Chapter")
                        }
                        if (showDropdown.value) {
                            DropdownMenu(
                                expanded = showDropdown.value,
                                onDismissRequest = { showDropdown.value = false },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                availableChapters.forEach { chapter ->
                                    DropdownMenuItem(
                                        onClick = {
                                            selectedChapterId.value = chapter.chapterId
                                            selectedChapterTitle.value = chapter.chapterTitle
                                            showDropdown.value = false
                                        }
                                    ) {
                                        Text(chapter.chapterTitle)
                                    }
                                }
                            }
                        }
                    }
                    Button(
                        onClick = {
                            if (selectedChapterId.value != null) {
                                viewModel.addNewOption(newOptionName.value, selectedChapterId.value!!)
                                newOptionName.value = ""
                                selectedChapterTitle.value = null
                                selectedChapterId.value = null
                                showDialog.value = false
                            }
                        },
                        enabled = newOptionName.value.isNotEmpty() && selectedChapterId.value != null,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Confirm")
                    }
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
        var isEnd by remember { mutableStateOf(state.thisChapter.isEnd == 1) }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { viewModel.setActiveScreen("StoryEditScreen") }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Edit Chapter",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                ChapterTitleSection(chapterTitle) { newChapterTitle ->
                    chapterTitle = newChapterTitle
                    viewModel.updateChapterTitle(chapterTitle)
                }

                Spacer(modifier = Modifier.height(16.dp))
                ContentSection(contentList = state.thisChapter.contentList, viewModel = viewModel)
                Spacer(modifier = Modifier.height(16.dp))

                // Checkbox Section
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Is the end")
                    Checkbox(
                        checked = isEnd,
                        onCheckedChange = { checked ->
                            isEnd = checked
                            viewModel.updateChapterIsEnd(if (checked) 1 else 0)
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Only show the Options Section if it's not the end
                if (!isEnd) {
                    OptionsSection(optionList = state.thisChapter.optionList, viewModel = viewModel)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        viewModel.updateChapterInList()
                        viewModel.printAuthorEditUiState()
                    },
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
fun AuthorEditMainScreen(viewModel: AuthorEditViewModel) {
    DarkTheme {
        AuthorEditScreen(viewModel)
    }
}

@Preview(showBackground = true)
@Composable
fun AuthorEditMainScreenPreview(){
    val viewModel = viewModel<AuthorEditViewModel>()
    AuthorEditMainScreen(viewModel)
}
