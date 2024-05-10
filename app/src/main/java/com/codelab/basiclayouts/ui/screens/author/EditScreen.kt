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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
            .fillMaxHeight(0.25f), // 占屏幕高度的1/2
        shape = MaterialTheme.shapes.medium, // 圆角
        color = MaterialTheme.colorScheme.surfaceVariant // 背景颜色与主题背景不同
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            contentList.forEach { content ->
                var text by remember { mutableStateOf(content.contentData) }
                TextField(
                    value = text,
                    onValueChange = {
                        text = it
                        viewModel.updateContent(content.contentId, text)
                    },
                    label = { Text("Edit Content") }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}



@Composable
fun OptionsSection(optionList: List<OptionAU>, viewModel: AuthorEditViewModel) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.25f), // 占屏幕高度的1/4
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
fun AuthorEditScreen(viewModel: AuthorEditViewModel) {
    AppTheme {
        val state by viewModel.authorEditUiState.collectAsState()
        var chapterTitle by remember { mutableStateOf(state.thisChapter.chapterTitle) }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                TextField(
                    value = chapterTitle,
                    onValueChange = { chapterTitle = it },
                    label = { Text("Chapter Title") },
                    colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        viewModel.updateChapterTitle(chapterTitle)
                    }),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                ContentSection(contentList = state.thisChapter.contentList, viewModel)
                Spacer(modifier = Modifier.height(16.dp))
                OptionsSection(optionList = state.thisChapter.optionList, viewModel)
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
