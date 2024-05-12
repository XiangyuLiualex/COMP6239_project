package com.codelab.basiclayouts.ui.uistate.reader

import com.codelab.basiclayouts.model.reader.readingPath
import com.codelab.basiclayouts.model.reader.readingPathItem

data class ReaderStoryHistoryUiState (
    val readerId : Int = -1,
    val storyId : Int = 1,
    val readingPath : List<readingPath> = listOf()
)