package com.codelab.basiclayouts.ui.uistate.reader

import com.codelab.basiclayouts.model.reader.readerStoryContent
import com.codelab.basiclayouts.model.reader.readerTChapter
import com.codelab.basiclayouts.model.reader.readerTOption
import com.codelab.basiclayouts.model.reader.readerTStorys

class StoryContentScreenUiState (
    val readerTStorys : readerTStorys,
    val readerTChapter : readerTChapter,
    val readerStoryContentList: List<readerStoryContent> = listOf(),
    val readerTOptionList: List<readerTOption> = listOf()
)