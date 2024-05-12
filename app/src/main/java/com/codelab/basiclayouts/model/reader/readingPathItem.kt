package com.codelab.basiclayouts.model.reader

import com.google.gson.annotations.SerializedName
import java.util.Date

/**
 * 阅读路径项实体类，用于描述阅读路径中的单个章节或内容。
 */
data class readingPathItem(
    /** 阅读路径项ID */
    @SerializedName("readingPathItemId") val readingPathItemId: Int = -1,

    /** 章节ID */
    @SerializedName("chapterId") val chapterId: Int = -1,

    /** 章节ID */
    @SerializedName("chapterName") val chapterName: String = "",

    /** 阅读次数 */
    @SerializedName("readingTimes") val readingTimes: Date? =null,

    /** 下一阅读路径ID */
    @SerializedName("nextReadingPathId") val nextReadingPathId: Int = -1,

    /** 阅读时间 */
    @SerializedName("readingTime") val readingTime: Date?=null,

    /** 顺序 */
    @SerializedName("order") val order: Int = -1,

    /** 所属章节路径记录头节点ID */
    @SerializedName("tReadingPathId") val tReadingPathId: Int = -1
)