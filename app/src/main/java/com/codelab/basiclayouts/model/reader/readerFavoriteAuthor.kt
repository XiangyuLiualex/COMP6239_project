package com.codelab.basiclayouts.model.reader

data class readerFavoriteAuthor(
    val userId: Int,
    val username: String,
    val realName: String,
    val telephone: String?,
    val photoUrl: String?,
    val isUsed: Int,
    val sex: Int?,
    val email: String?,
    val order: Int?,
    val selfDescription: String = "",
    val birthday: String?,
    val ifFavorite: Boolean = false
)
