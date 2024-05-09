package com.codelab.basiclayouts.data

data class ResultData<T>(
    val code: Int,
    val sym: String,
    val msg: String,
    val data: T?
)