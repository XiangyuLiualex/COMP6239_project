package com.codelab.basiclayouts.model.reader

data class readerFavoriteAuthor(
    val userId: Int = -1,
    val username: String="",
    val realName: String?="",
    val telephone: String?="",
    val photoUrl: String?="",
    val isUsed: Int = -1,
    val sex: Int? = -1,
    val email: String?="",
    val order: Int? = -1,
    val selfDescription: String = "",
    val birthday: String?="",
    val ifFavorite: Boolean = false
)
