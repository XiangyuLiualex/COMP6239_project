package com.codelab.basiclayouts.ui.uistate.reader

import com.codelab.basiclayouts.model.reader.readerTStorys
import com.codelab.basiclayouts.model.reader.readerTStorysForUiState

data class ReaderLibraryScreenUiState(
    val readerId : Int = 1,
    val readerTStorys: List<readerTStorysForUiState> = listOf(),
    )
