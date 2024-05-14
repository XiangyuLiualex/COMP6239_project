package com.codelab.basiclayouts.model.reader

import com.google.gson.annotations.SerializedName
import java.util.Date

/**
 * 阅读路径实体类，用于描述读者在故事中的选择路径。
 */
data class readingPath(
    /** 阅读路径ID */
    @SerializedName("readingPathId") val readingPathId: Int? = null,

    /** 开始时间 */
    @SerializedName("startTime") val startTime: Date? = null,

    /** 更新时间 */
    @SerializedName("updateTime") val updateTime: Date? = null,

    /** 故事ID */
    @SerializedName("storyId") val storyId: Int = -1,

    /** 起始阅读路径项ID */
    @SerializedName("startReadingPathItemId") val startReadingPathItemId: Int? = -1,

    /** 读者ID */
    @SerializedName("readerId") val readerId: Int = -1,

    /** 阅读路径项列表 */
    @SerializedName("readingPathItemList") val readingPathItemList : List<readingPathItem> = listOf()
)