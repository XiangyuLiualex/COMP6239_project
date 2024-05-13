package com.codelab.basiclayouts.ui.screens.author

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codelab.basiclayouts.ui.theme.AppTheme
import com.codelab.basiclayouts.ui.uistate.author.CategoryAU
import com.codelab.basiclayouts.ui.uistate.author.StoryAU
import com.codelab.basiclayouts.ui.viewmodel.author.AuthorEditViewModel
import kotlin.random.Random

@Composable
fun NewStoryScreen(viewModel: AuthorEditViewModel) {
    var storyName by remember { mutableStateOf("") }
    var storyDescription by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<CategoryAU?>(null) }
    var showCategoryMenu by remember { mutableStateOf(false) }

    val uiState = viewModel.authorEditUiState.collectAsState().value

    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(
                        onClick = { viewModel.setActiveScreen("AuthorMainScreen") }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
//                    Spacer(modifier = Modifier.weight(0.2f))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "New Story",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = storyName,
                    onValueChange = { storyName = it },
                    label = { Text("Story Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = storyDescription,
                    onValueChange = { storyDescription = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (selectedCategory != null) {
                    Text(
                        text = "Selected Category: ${selectedCategory?.categoryName}",
                        modifier = Modifier.align(Alignment.Start)
                    )
                }

                Box {
                    Button(
                        onClick = { showCategoryMenu = !showCategoryMenu },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Select a category")
                        Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                    }

                    DropdownMenu(
                        expanded = showCategoryMenu,
                        onDismissRequest = { showCategoryMenu = false },
                        modifier = Modifier.align(Alignment.TopStart)
                    ) {
                        uiState.categoryList.forEach { category ->
                            DropdownMenuItem(
                                text = { Text(category.categoryName) },
                                onClick = {
                                    selectedCategory = category
                                    showCategoryMenu = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (storyName.isNotEmpty() && selectedCategory != null) {
                            val newStory = StoryAU(
                                storyId = -Random.nextInt(1, Int.MAX_VALUE),
                                storyName = storyName,
                                storyDescription = storyDescription,
                                storyCategory = selectedCategory!!.categoryId,
                                chapterList = listOf(),
                                authorId = uiState.authorId,
                                isUsed = 2
                            )
                            viewModel.addStory(newStory)
                            viewModel.setThisStory(newStory)
                            viewModel.setActiveScreen("StoryEditScreen")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Start Writing")
                }
            }
        }
    }
}
