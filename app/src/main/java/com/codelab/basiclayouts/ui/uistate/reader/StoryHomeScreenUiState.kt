package com.codelab.basiclayouts.ui.uistate.reader

import com.codelab.basiclayouts.model.reader.readerTStorysForUiState

data class StoryHomeScreenUiState(
    val readerId: Int = 1,
    val readerTStorys: List<readerTStorysForUiState> = listOf(),
)
