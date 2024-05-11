package com.codelab.basiclayouts.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.codelab.basiclayouts.ui.theme.AppTheme
import com.codelab.basiclayouts.ui.uistate.author.ChapterAU
import com.codelab.basiclayouts.ui.viewmodel.author.AuthorEditViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.random.Random

@Composable
fun StoryEditScreen(viewModel: AuthorEditViewModel) {
    val uiState = viewModel.authorEditUiState.collectAsState().value
    var isPublished by remember { mutableStateOf(uiState.thisStory.isUsed == 1) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Add back button and title
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
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Edit Story",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            StoryHeader(uiState.thisStory.storyName)

            ChapterListBox(
                viewModel = viewModel,
                chapterList = uiState.thisStory.chapterList,
                modifier = Modifier.weight(0.7f),
                onSelectChapter = { chapter ->
                    viewModel.setCurrentChapter(chapter)
                    viewModel.setActiveScreen("AuthorEditMainScreen")
                }
            )

            // Add chapter button
            Button(
                onClick = { /* showDialog logic */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("Add New Chapter")
            }

            // Save button
            Button(
                onClick = {
                    viewModel.updateStoryInList()
                    viewModel.printAuthorEditUiState()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("Save")
            }

            // Publish Checkbox
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Checkbox(
                    checked = isPublished,
                    onCheckedChange = { checked ->
                        isPublished = checked
                        viewModel.updatePublicationStatus(isPublished)
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Publish")
            }
        }
    }
}




@Composable
fun StoryHeader(storyName: String) {
    Text(
        text = storyName,
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.padding(top = 16.dp)
    )
}

@Composable
fun ChapterListBox(
    viewModel: AuthorEditViewModel,
    chapterList: List<ChapterAU>,
    modifier: Modifier = Modifier,
    onSelectChapter: (ChapterAU) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(8.dp)
        ) {
            chapterList.forEach { chapter ->
                Button(
                    onClick = { onSelectChapter(chapter) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(chapter.chapterTitle)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewChapterDialog(onDismiss: () -> Unit, onConfirm: (String) -> Unit, chapterList: List<ChapterAU>) {
    val chapterTitle = remember { mutableStateOf("") }
    val isTitleInvalid = remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("New Chapter", style = MaterialTheme.typography.headlineMedium)

                OutlinedTextField(
                    value = chapterTitle.value,
                    onValueChange = { input ->
                        chapterTitle.value = input
                        isTitleInvalid.value = chapterList.any { it.chapterTitle == input }
                    },
                    label = { Text("Chapter Title") },
                    isError = isTitleInvalid.value,
                    modifier = Modifier.fillMaxWidth()
                )
                if (isTitleInvalid.value) {
                    Text("Invalid name", color = MaterialTheme.colorScheme.error)
                }

                Button(
                    onClick = { onConfirm(chapterTitle.value) },
                    enabled = chapterTitle.value.isNotEmpty() && !isTitleInvalid.value,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Confirm")
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun StoryEditScreenPreview() {
    val viewModel = viewModel<AuthorEditViewModel>()
    StoryEditScreen(viewModel)
}
