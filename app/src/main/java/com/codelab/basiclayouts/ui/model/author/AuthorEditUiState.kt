package com.codelab.basiclayouts.ui.model.author

data class AuthorEditUiState(
    val chapterName: String = "",
    val textFields: List<String> = listOf(),
    val options: List<Option> = listOf(),  // 存储选项
    val isTheEnd: Boolean = false,
    val isNavigationVisible: Boolean = false
)

data class Option(
    val name: String,
    val relatedChapterId: String
)

