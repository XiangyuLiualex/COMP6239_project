package com.codelab.basiclayouts.model.reader

import com.google.gson.annotations.SerializedName

/**
 * 选项实体类，用于描述章节中的不同选择路径。
 */
data class readerTOption(
    /** 选项ID */
    @SerializedName("optionId") val optionId: Int,

    /** 选项名称 */
    @SerializedName("optionName") val optionName: String,

    /** 所属章节ID */
    @SerializedName("chapterId") val chapterId: Int,

    /** 选项描述，提供关于选项的额外信息，可选 */
    @SerializedName("optionDescription") val optionDescription: String?,

    /** 指向下一章节的ID，用于决定选择此选项后的流程走向 */
    @SerializedName("nextChapterId") val nextChapterId: Int,

    /** 是否使用，通常用于标记选项是否激活或可用，1表示使用，0表示未使用 */
    @SerializedName("isUsed") val isUsed: Int,

    /** 顺序，决定选项显示的顺序 */
    @SerializedName("order") val order: Int
)
