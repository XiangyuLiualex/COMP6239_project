package code.ui.screen

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
import code.ui.theme.AppTheme
import code.ui.uistate.author.ChapterAU
import code.ui.viewmodel.author.AuthorEditViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.random.Random

// Modify the StoryEditScreen function to handle the new onConfirm parameters
@Composable
fun StoryEditScreen(viewModel: AuthorEditViewModel) {
    AppTheme {
        val uiState by viewModel.authorEditUiState.collectAsState()
        val showDialog = remember { mutableStateOf(false) }
        var isPublished by remember { mutableStateOf(uiState.thisStory.isUsed == 1) }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 添加后退按钮
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(
                        onClick = { viewModel.getStoryList(uiState.authorId)
                            viewModel.setActiveScreen("AuthorMainScreen") }
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
                    modifier = Modifier.height(350.dp),
                    onSelectChapter = { chapter ->
                        viewModel.setCurrentChapter(chapter)
                        viewModel.setActiveScreen("AuthorEditMainScreen")
                    }
                )

                Button(
                    onClick = { showDialog.value = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("Add New Chapter")
                }

                Button(
                    onClick = {
                        viewModel.updateStoryInList()
                        viewModel.printAuthorEditUiState()
                        viewModel.saveThisStory(uiState.thisStory)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("Save")
                }

                // Publish Checkbox
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isPublished,
                        onCheckedChange = { checked ->
                            isPublished = checked
                            if (checked) {
                                viewModel.publishStory()
                            } else {
                                viewModel.unpublishStory()
                            }
                        }
                    )
                    Text("Publish", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }

        if (showDialog.value) {
            AddNewChapterDialog(
                onDismiss = { showDialog.value = false },
                onConfirm = { chapterTitle, isStartChapter -> // Update parameters
                    val newChapter = ChapterAU(
                        chapterId = Random.nextInt(1, 100000),
                        chapterTitle = chapterTitle,
                        storyId = uiState.thisStory.storyId,
                        contentList = listOf(),
                        optionList = listOf(),
                        isEnd = if (isStartChapter) 2 else 0 // Set isEnd based on checkbox state
                    )
                    viewModel.addChapter(newChapter)
                    showDialog.value = false
                },
                chapterList = uiState.thisStory.chapterList
            )
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
fun AddNewChapterDialog(onDismiss: () -> Unit, onConfirm: (String, Boolean) -> Unit, chapterList: List<ChapterAU>) {
    val chapterTitle = remember { mutableStateOf("") }
    val isTitleInvalid = remember { mutableStateOf(false) }
    val isStartChapter = remember { mutableStateOf(false) } // Add this line

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

                // Add Checkbox
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isStartChapter.value,
                        onCheckedChange = { isStartChapter.value = it }
                    )
                    Text("Is start chapter")
                }

                Button(
                    onClick = { onConfirm(chapterTitle.value, isStartChapter.value) }, // Pass the checkbox state to onConfirm
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