package com.codelab.basiclayouts.ui.uistate.reader

import com.codelab.basiclayouts.model.reader.readerTStorys

data class ReaderLibraryScreenUiState(
    val readerId : Int = 1,
    val readerTStorys: List<readerTStorys> = listOf(),
    )
