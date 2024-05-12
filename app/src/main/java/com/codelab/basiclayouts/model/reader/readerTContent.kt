package com.codelab.basiclayouts.model.reader

import com.google.gson.annotations.SerializedName

/**
 * 内容实体类，用于描述章节中的具体内容。
 */
data class readerTContent(
    /** 内容ID */
    @SerializedName("contentId") val contentId: Int = -1,

    /** 章节ID */
    @SerializedName("chapterId") val chapterId: Int = -1,

    /** 顺序，决定内容显示的顺序 */
    @SerializedName("order") val order: Int = -1,

    /** 内容类型，0：文字，1：图片，2：视频 */
    @SerializedName("contentType") val contentType: Int = -1,

    /** 具体内容，可以是文字、图片路径或视频路径 */
    @SerializedName("contentData") val contentData: String="",

    /** 是否使用，通常用于标记内容是否激活或可用，1表示使用，0表示未使用 */
    @SerializedName("isUsed") val isUsed: Int = -1,

    /** 内容描述，对内容的额外说明，可选 */
    @SerializedName("contentDescription") val contentDescription: String?="",

    /** 内容小标题，提供内容的简短描述，可选 */
    @SerializedName("contentTitle") val contentTitle: String?="",

    /** 背景音乐，为内容设置背景音乐的路径，可选 */
    @SerializedName("music") val music: String?=""
)
