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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthorMainScreen(viewModel: AuthorEditViewModel,navHostController: NavHostController) {
    AppTheme {
        val uiState by viewModel.authorEditUiState.collectAsState()

        // 筛选不同状态的故事
        val draftStories = uiState.storyList.filter { it.isUsed == 2 }
        val publishedStories = uiState.storyList.filter { it.isUsed == 1 }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                TopAppBar(
                    title = { Text(text = "Author Dashboard") },
                    actions = {
                        IconButton(onClick = { navHostController.navigate("MainScreen") }) {
                            Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = "Sign Out")
                        }
                    }
                )
            }
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
                        .weight(0.2f)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(vertical = 8.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Column {
                        draftStories.forEach { story ->
                            Button(
                                onClick = { viewModel.selectStory(story)
                                    viewModel.setActiveScreen("StoryEditScreen")
                                },
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
                        .weight(0.3f)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(vertical = 8.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Column {
                        publishedStories.forEach { story ->
                            Button(
                                onClick = { viewModel.selectStory(story)
                                    viewModel.setActiveScreen("StoryStatisticsScreen") },
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

//@Preview(showBackground = true)
//@Composable
//fun PreviewAuthorMainScreen() {
//    val viewModel = viewModel<AuthorEditViewModel>()
//    DarkTheme {
//        AuthorMainScreen(viewModel)
//    }
//}
