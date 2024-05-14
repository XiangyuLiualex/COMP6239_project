package com.codelab.basiclayouts.ui.uistate.reader

import com.codelab.basiclayouts.R
import com.codelab.basiclayouts.model.reader.readerTStorysForUiState

data class StoryHomeScreenUiState(
    val readerId: Int = R.integer.READERID,
    val readerTStorys: List<readerTStorysForUiState> = listOf(),
)
