package com.codelab.basiclayouts.ui.screens.author

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codelab.basiclayouts.ui.theme.AppTheme
import com.codelab.basiclayouts.ui.theme.DarkTheme
import com.codelab.basiclayouts.ui.viewmodel.author.AuthorEditViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthorMainScreen(viewModel: AuthorEditViewModel) {
    AppTheme {
        val uiState by viewModel.authorEditUiState.collectAsState()

        // 筛选不同状态的故事
        val draftStories = uiState.storyList.filter { it.isUsed == 2 }
        val publishedStories = uiState.storyList.filter { it.isUsed == 1 }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background
        ) { padding ->
            Column(modifier = Modifier.padding(padding).padding(16.dp)) {
                Text(text = "New Story:", style = MaterialTheme.typography.headlineMedium)
                Button(
                    onClick = { viewModel.setActiveScreen("NewStoryScreen")},
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                ) {
                    Text(text = "Create a new Story")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Draft Stories:", style = MaterialTheme.typography.headlineMedium)
                Box(
                    modifier = Modifier
                        .weight(0.75f)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(vertical = 8.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Column {
                        draftStories.forEach { story ->
                            Button(
                                onClick = { viewModel.selectStory(story) },
                                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                            ) {
                                Text(text = story.storyName)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Published Book:", style = MaterialTheme.typography.headlineMedium)
                Box(
                    modifier = Modifier
                        .weight(0.75f)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(vertical = 8.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Column {
                        publishedStories.forEach { story ->
                            Button(
                                onClick = { /* 处理选择已发布书籍的逻辑 */ },
                                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                            ) {
                                Text(text = story.storyName)
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewAuthorMainScreen() {
    val viewModel = viewModel<AuthorEditViewModel>()
    DarkTheme {
        AuthorMainScreen(viewModel)
    }
}
