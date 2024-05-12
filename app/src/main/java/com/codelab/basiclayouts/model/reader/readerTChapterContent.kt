package com.codelab.basiclayouts.model.reader

import com.google.gson.annotations.SerializedName

/**
 * 章节及其内容实体类，用于描述一个章节及其所有相关内容。
 */
data class readerTChapterContent(
    /** 章节ID */
    @SerializedName("chapterId") val chapterId: Int,

    /** 章节名称 */
    @SerializedName("chapterTitle") val chapterTitle: String,

    /** 章节编号，用于表示章节的序列 */
    @SerializedName("chapterNumber") val chapterNumber: Int,

    /** 所属故事ID */
    @SerializedName("storyId") val storyId: Int,

    /** 备注信息，可以用来存放额外信息或解释 */
    @SerializedName("notes") val notes: String?,

    /** 章节关联的背景音乐URL */
    @SerializedName("music") val music: String?,

    /** 是否为结束章节，通常用于标识章节是否是故事的终点 */
    @SerializedName("isEnd") val isEnd: Int,

    /** 是否使用该章节，用于控制章节是否显示或可访问 */
    @SerializedName("isUsed") val isUsed: Int,

    /** 包含的内容列表，每个元素为一个 TContent 对象 */
    @SerializedName("tContentList") val tContentList: List<readerTContent>
)
