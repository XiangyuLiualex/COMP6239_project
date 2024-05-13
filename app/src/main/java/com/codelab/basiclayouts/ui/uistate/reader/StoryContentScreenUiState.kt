package com.codelab.basiclayouts.ui.uistate.reader

import com.codelab.basiclayouts.model.reader.readerTChapter
import com.codelab.basiclayouts.model.reader.readerTContent
import com.codelab.basiclayouts.model.reader.readerTOption
import com.codelab.basiclayouts.model.reader.readerTStorys

data class StoryContentScreenUiState (
    val readerTStorys : readerTStorys,
    val readerTChapter : readerTChapter,
    val readerTContentList: List<readerTContent> = listOf(),
    val readerTOptionList: List<readerTOption> = listOf()
)