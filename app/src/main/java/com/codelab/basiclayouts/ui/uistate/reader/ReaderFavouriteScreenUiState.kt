package com.codelab.basiclayouts.ui.uistate.reader

import com.codelab.basiclayouts.model.reader.FavoriteAuthor


// 正确的方式：在主构造函数中定义参数
data class ReaderFavouriteScreenUiState(
    val authors: List<FavoriteAuthor> = listOf(),

    )
