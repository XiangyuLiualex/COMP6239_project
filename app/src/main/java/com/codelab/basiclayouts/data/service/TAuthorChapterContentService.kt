package com.codelab.basiclayouts.data.service

import com.codelab.basiclayouts.data.ResultData
import com.codelab.basiclayouts.model.reader.readerTStorys
import com.codelab.basiclayouts.ui.uistate.author.ChapterAU
import com.codelab.basiclayouts.ui.uistate.author.StoryAU
import retrofit2.http.Body
import retrofit2.http.POST

interface TAuthorChapterContentService {

    @POST("/TAuthorChapterContentCtrl/tAuthorUpdateStory")
    suspend fun tAuthorUpdateStory(@Body values: StoryAU): ResultData<Int>


    @POST("/TAuthorChapterContentCtrl/tAuthorStorysByAuthorId")
    suspend fun tAuthorStorysByAuthorId(@Body values: Map<String, Int>): ResultData<List<StoryAU>>
    @POST("/TAuthorChapterContentCtrl/tRootAuthorStoryByStoryId")
    suspend fun tRootAuthorStoryByStoryId(@Body values: Map<String, Int>): ResultData<StoryAU>

    @POST("/TAuthorChapterContentCtrl/tAuthorStoryByStoryId")
    suspend fun tAuthorStoryByStoryId(@Body values: Map<String, Int>): ResultData<StoryAU>

    @POST("/TAuthorChapterContentCtrl/tAuthorChapterByChapterId")
    suspend fun tAuthorChapterByChapterId(@Body values: Map<String, Int>): ResultData<ChapterAU>

    @POST("/TAuthorChapterContentCtrl/tAuthorChapterListByStoryId")
    suspend fun tAuthorChapterListByStoryId(@Body values: Map<String, Int>): ResultData<ChapterAU>


}