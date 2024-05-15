package code.ui.uistate.reader

import com.codelab.basiclayouts.R
import code.model.reader.readingPath

data class ReaderStoryHistoryUiState (
    val readerId : Int = R.integer.READERID,
    val storyId : Int = 1,
    val readingPath : List<readingPath> = listOf()
)