package com.codelab.basiclayouts.ui.uistate.reader

import com.codelab.basiclayouts.R
import com.codelab.basiclayouts.model.reader.readerTChapter
import com.codelab.basiclayouts.model.reader.readerTContent
import com.codelab.basiclayouts.model.reader.readerTOption
import com.codelab.basiclayouts.model.reader.readerTStorys

data class StoryContentScreenUiState (
    val readerId : Int = R.integer.READERID,
    val storyId : Int = 1,
    val chapterId : Int = 1,
    val readingPathId : Int = 1,//头节点ID
    val readerTStorys : readerTStorys,
    val readerTChapter : readerTChapter,
    val readerTContentList: List<readerTContent> = listOf(),
    val readerTOptionList: List<readerTOption> = listOf()
)