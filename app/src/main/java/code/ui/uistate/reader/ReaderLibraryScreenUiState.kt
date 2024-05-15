package code.ui.uistate.reader

import com.codelab.basiclayouts.R
import code.model.reader.readerTStorysForUiState

data class ReaderLibraryScreenUiState(
    val readerId : Int = R.integer.READERID,
    val readerTStorys: List<readerTStorysForUiState> = listOf(),
    )
