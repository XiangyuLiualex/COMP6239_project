package com.codelab.basiclayouts.model.reader

import com.google.gson.annotations.SerializedName
import java.util.Date

/**
 * 阅读路径项实体类，用于描述阅读路径中的单个章节或内容。
 */
data class readingPathItem(
    /** 阅读路径项ID */
    @SerializedName("readingPathItemId") val readingPathItemId: Int,

    /** 章节ID */
    @SerializedName("chapterId") val chapterId: Int,

    /** 阅读次数 */
    @SerializedName("readingTimes") val readingTimes: Date,

    /** 下一阅读路径ID */
    @SerializedName("nextReadingPathId") val nextReadingPathId: Int,

    /** 阅读时间 */
    @SerializedName("readingTime") val readingTime: Date,

    /** 顺序 */
    @SerializedName("order") val order: Int
)