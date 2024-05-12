package com.codelab.basiclayouts.data.service

import com.codelab.basiclayouts.data.ResultData
import com.codelab.basiclayouts.model.reader.readerTChapter
import com.codelab.basiclayouts.model.reader.readerTContent
import com.codelab.basiclayouts.model.reader.readerTOption
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

interface TChapterContentService {
//根据故事ID显示本故事的所有章节
    @POST("/TChapterContentCtrl/tChapterListByStoryId")
    suspend fun tChapterListByStoryId(@Body values: Map<String, Any>): ResultData<List<readerTChapter>>

//根据章节ID显示本章节的所有内容
    @POST("/TChapterContentCtrl/tContentListByChapterId")
    suspend fun tContentListByChapterId(@Body values: Map<String, Any>): ResultData<List<readerTContent>>

//    根据章节ID显示本章节的所有选项
    @POST("/TChapterContentCtrl/tOptionListByChapterId")
    suspend fun tOptionListByChapterId(@Body values: Map<String, Any>): ResultData<List<readerTOption>>

//    根据故事ID和章节名模糊查询章节
    @POST("/TChapterContentCtrl/tChapterByStoryIdAndChapterTitle")
    suspend fun tChapterByStoryIdAndChapterTitle(@Body values: Map<String, Any>): ResultData<readerTChapter>
}
