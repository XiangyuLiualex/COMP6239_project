package com.codelab.basiclayouts.ui.screens.author

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.codelab.basiclayouts.ui.screen.AuthorEditMainScreen
import com.codelab.basiclayouts.ui.screen.StoryEditScreen
import com.codelab.basiclayouts.ui.viewmodel.author.AuthorEditViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ParentScreen(viewModel: AuthorEditViewModel = viewModel()) {
    val activeScreen by viewModel.activeScreen.collectAsState()

    when (activeScreen) {
        "AuthorMainScreen" ->AuthorMainScreen(viewModel)
        "StoryStatisticsScreen" -> StoryStatisticsScreen(viewModel)
        "NewStoryScreen" -> NewStoryScreen(viewModel)
        "StoryEditScreen" -> StoryEditScreen(viewModel)
        "AuthorEditMainScreen" -> AuthorEditMainScreen(viewModel)
    }
}