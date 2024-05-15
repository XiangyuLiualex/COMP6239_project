package code.ui.uistate.reader

import com.codelab.basiclayouts.R
import code.model.reader.readerTChapter
import code.model.reader.readerTContent
import code.model.reader.readerTOption
import code.model.reader.readerTStorys

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