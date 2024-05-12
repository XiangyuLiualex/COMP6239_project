package com.codelab.basiclayouts.ui.uistate.reader

import com.codelab.basiclayouts.model.reader.readerFavoriteAuthor


data class ReaderFavouriteScreenUiState(
    val authors: List<readerFavoriteAuthor> = listOf(),

    )
