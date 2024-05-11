package com.codelab.basiclayouts.ui.screens.author

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codelab.basiclayouts.ui.theme.AppTheme
import com.codelab.basiclayouts.ui.theme.DarkTheme
import com.codelab.basiclayouts.ui.viewmodel.author.AuthorEditViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoryStatisticsScreen(viewModel: AuthorEditViewModel = viewModel()) {
    AppTheme {
        val uiState by viewModel.authorEditUiState.collectAsState()
        val story = uiState.thisStory

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Story Statistics", style = MaterialTheme.typography.headlineMedium) },
                    navigationIcon = {
                        IconButton(onClick = { viewModel.setActiveScreen("AuthorMainScreen") }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            },
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // 故事标题
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = story.storyName,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = story.storyDescription,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 章节统计
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Number of Chapters",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = story.chapterList.size.toString(),
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }

                // 你可以继续添加其他统计信息或内容，以填充页面
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStoryStatisticsScreen() {
    val viewModel = viewModel<AuthorEditViewModel>()
    DarkTheme {
        StoryStatisticsScreen(viewModel)
    }
}
