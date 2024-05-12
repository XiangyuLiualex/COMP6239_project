package com.codelab.basiclayouts.model.reader

import com.google.gson.annotations.SerializedName

/**
 * 读者故事历史实体类，用于跟踪读者的阅读进度和历史记录。
 */
data class readerStoryHistory(
    /** 读者ID */
    @SerializedName("readerId") val readerId: Int = -1,

    /** 故事ID */
    @SerializedName("storyId") val storyId: Int = -1,

    /** 是否正在使用 */
    @SerializedName("isUsed") val isUsed: Int = -1,

    /** 笔记 */
    @SerializedName("notes") val notes: String? = null,

    /** 当前进度 */
    @SerializedName("currentProgress") val currentProgress: Int = -1
)