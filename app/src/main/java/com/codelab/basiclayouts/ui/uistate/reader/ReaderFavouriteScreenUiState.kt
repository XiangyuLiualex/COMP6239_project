package com.codelab.basiclayouts.ui.uistate.reader

import com.codelab.basiclayouts.R
import com.codelab.basiclayouts.model.reader.readerFavoriteAuthor


data class ReaderFavouriteScreenUiState(
    val readerId : Int = R.integer.READERID,
    val storyId : Int = 1,
    val authors: List<readerFavoriteAuthor> = listOf(),
    )
