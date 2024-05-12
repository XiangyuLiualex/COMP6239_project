package com.codelab.basiclayouts.model.reader

import com.google.gson.annotations.SerializedName

/**
 * 章节实体类
 *
 * @property chapterId 章节ID
 * @property chapterTitle 章节名
 * @property chapterNumber 章节编号
 * @property storyId 所属故事ID
 * @property notes 备注
 * @property music 音乐
 * @property isEnd 是否为结束章节，1 表示是，0 表示否
 * @property isUsed 是否使用，1 表示使用，0 表示未使用
 */
data class readerTChapter(

    @SerializedName("chapterId") val chapterId: Int,

    @SerializedName("chapterTitle") val chapterTitle: String,

    @SerializedName("chapterNumber") val chapterNumber: Int,

    @SerializedName("storyId") val storyId: Int,

    @SerializedName("notes") val notes: String?,

    @SerializedName("music") val music: String?,

    @SerializedName("isEnd") val isEnd: Int,

    @SerializedName("isUsed") val isUsed: Int
)
