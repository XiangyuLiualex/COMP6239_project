package com.codelab.basiclayouts.model.reader

import com.google.gson.annotations.SerializedName

/**
 * 剧本实体类，用于描述故事的基本信息。
 */
data class readerTStorys(
    /** 剧本ID */
    @SerializedName("storyId") val storyId: Int = -1,

    /** 剧本名称 */
    @SerializedName("storyName") val storyName: String = "",

    /** 剧本简介，提供剧本的基本概况 */
    @SerializedName("storyDescription") val storyDescription: String = "",

    /** 剧本热度，表示剧本的流行程度或者用户的关注度 */
    @SerializedName("storyTrends") val storyTrends: Int = -1,

    /** 剧本封面URL，用于展示剧本封面图片 */
    @SerializedName("storyCoverUrl") val storyCoverUrl: String = ""
)
