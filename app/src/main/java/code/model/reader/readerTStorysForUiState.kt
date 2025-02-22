package code.model.reader

import com.google.gson.annotations.SerializedName

/**
 * 剧本实体类，用于描述故事的基本信息。
 */
data class readerTStorysForUiState(
    /** 剧本ID */
    @SerializedName("storyId") val storyId: Int = -1,

    /** 剧本名称 */
    @SerializedName("storyName") val storyName: String="",

    /** 剧本简介，提供剧本的基本概况 */
    @SerializedName("storyDescription") val storyDescription: String ="",

    /** 剧本热度，表示剧本的流行程度或者用户的关注度 */
    @SerializedName("storyTrends") val storyTrends: Int = -1,

    /** 剧本封面URL，用于展示剧本封面图片 */
    @SerializedName("storyCoverUrl") val storyCoverUrl: String? = "",

    /** 剧本作者ID */
    @SerializedName("authorId") val authorId: Int = -1,

    //以下是要从其他表中取得的信息

    /** 剧本作者 */
    @SerializedName("author") val author: String?="",

    /** 剧本阅读进度，0未看，1正在看，2已看完 */
    @SerializedName("currentProgress") val currentProgress: Int = -1,

    /** 剧本阅读进度 */
    @SerializedName("currentProgressText") val currentProgressText: String? = "",

    /** 剧本当前被阅读到哪个章节 */
    @SerializedName("currentChapterId") val currentChapterId: Int = 0,

    /** 剧本当前被阅读到哪个章节 */
    @SerializedName("currentChapterName") val currentChapterName: String = "",

    /** 剧本当前被阅读到哪个章节的ID,所对应的历史记录头节点的ID */
    @SerializedName("currentReadingPathId") val currentReadingPathId: Int? = 0,

    /** 剧本当前最新的章节历史记录头节点列表 */
    @SerializedName("readingPathList") val readingPathList  : List<readingPath> = listOf(),//头节点
)
