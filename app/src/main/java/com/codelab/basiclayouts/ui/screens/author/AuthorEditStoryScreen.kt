package com.codelab.basiclayouts.ui.screens.author

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Dataset
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.basiclayouts.ui.theme.AppTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Dialog
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.codelab.basiclayouts.R
import com.codelab.basiclayouts.ui.model.author.AuthorEditUiState
import com.codelab.basiclayouts.ui.viewmodel.author.AuthorEditStoryViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun AuthorEditStoryScreen( viewModel: AuthorEditStoryViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        bottomBar = { BottomNavigation() }
    ) { padding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                if (uiState.isNavigationVisible) {
                    SootheNavigationRail(viewModel)
                }
                IconButton(
                    onClick = { viewModel.toggleNavigationVisibility() }
                ) {
                    Icon(
                        imageVector = if (uiState.isNavigationVisible) Icons.Filled.Close else Icons.Filled.Menu,
                        contentDescription = "Toggle navigation"
                    )
                }
                EditComponent(Modifier.fillMaxHeight(), viewModel)
            }
        }
    }
}

@Composable
fun EditComponent(modifier: Modifier = Modifier, viewModel: AuthorEditStoryViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = uiState.chapterName,
            onValueChange = viewModel::updateChapterName,
            label = { Text("Chapter Name") }
        )
        AddElementMenu(uiState, viewModel)
        Checkbox(
            checked = uiState.isTheEnd,
            onCheckedChange = { viewModel.toggleEndChapter() }
        )
        Text("Is the end")
        Button(onClick = {}) {
            Text("Save")
        }
    }
}

@Composable
fun AddElementMenu(uiState: AuthorEditUiState, viewModel: AuthorEditStoryViewModel) {
    val expanded = remember { mutableStateOf(false) }
    val showOptionDialog = remember { mutableStateOf(false) }

    if (showOptionDialog.value) {
        OptionFormDialog(viewModel = viewModel, onDismiss = { showOptionDialog.value = false })
    }
    // 展示文本字段和选项按钮
    uiState.textFields.forEachIndexed { index, text ->
        RemovableTextField(
            text = text,
            onTextChange = { newText -> viewModel.updateTextField(index, newText) },
            onRemove = { viewModel.removeTextField(index) }
        )
    }
    uiState.options.forEach { option ->
        OptionButton(option.name, onRemove = { viewModel.removeOption(option.name) })
    }

    Column {
        ExtendedFloatingActionButton(onClick = { expanded.value = true }) {
            Icon(Icons.Filled.Add, contentDescription = "Add Element")
            Text("Add Element")
        }

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            DropdownMenuItem(
                text = { Text("Text") },
                onClick = {
                    viewModel.addTextField()
                    expanded.value = false
                },
                leadingIcon = { Icon(Icons.Outlined.Edit, contentDescription = null) }
            )
            DropdownMenuItem(
                text = { Text("Media") },
                onClick = { /* Handle Media Addition */ },
                leadingIcon = { Icon(Icons.Outlined.Link, contentDescription = null) }
            )
            DropdownMenuItem(
                text = { Text("Options") },
                onClick = {
                    showOptionDialog.value = true
                    expanded.value = false
                },
                leadingIcon = { Icon(Icons.Outlined.Dataset, contentDescription = null) }
            )
        }
    }
}


@Composable
fun OptionFormDialog(viewModel: AuthorEditStoryViewModel, onDismiss: () -> Unit) {
    var optionName by rememberSaveable { mutableStateOf("") }
    var relatedChapterId by rememberSaveable { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Option") },
        text = {
            Column {
                TextField(
                    value = optionName,
                    onValueChange = { optionName = it },
                    label = { Text("Option Name") }
                )
                TextField(
                    value = relatedChapterId,
                    onValueChange = { relatedChapterId = it },
                    label = { Text("Related Chapter ID") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    viewModel.addOption(optionName, relatedChapterId)
                    onDismiss()
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}


@Composable
fun RemovableTextField(text: String, onTextChange: (String) -> Unit, onRemove: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        TextField(
            value = text,
            onValueChange = onTextChange,
            label = { Text("Enter text") }
        )
        IconButton(onClick = onRemove) {
            Icon(Icons.Filled.Close, contentDescription = "Remove")
        }
    }
}

@Composable
fun OptionButton(optionName: String, onRemove: () -> Unit) {
    Row {
        Button(onClick = {}){
            Text(optionName)
        }
        IconButton(onClick = onRemove) {
            Icon(Icons.Filled.Close, contentDescription = "Remove")
        }
    }
}


@Composable
fun SootheNavigationRail(viewModel: AuthorEditStoryViewModel) {
    NavigationRail(
        modifier = Modifier.padding(start = 4.dp, end = 4.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                icon = { Icon(Icons.Default.Spa, contentDescription = null) },
                label = { Text("Home") },
                selected = false,
                onClick = { /* ViewModel can handle these actions if needed */ }
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                icon = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
                label = { Text("Profile") },
                selected = false,
                onClick = { /* ViewModel can handle these actions if needed */ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthorEditStoryScreenPreview() {
    AuthorEditStoryScreen()
}
