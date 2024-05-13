package com.codelab.basiclayouts.model

data class Story(
    val title: String,
    val author: String,
    val imageUrl: String,
    val description: String,
    val progress: String,
    val currentChapter: String,
    val category: String
)
