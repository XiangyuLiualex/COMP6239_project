package com.codelab.basiclayouts.model.reader

import com.codelab.basiclayouts.model.Author


// 正确的方式：在主构造函数中定义参数
data class ReaderFavouriteScreenUiState(
    val authors: List<Author> = listOf()
)
