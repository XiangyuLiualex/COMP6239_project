package code.data.service

import code.data.ResultData
import code.model.reader.readerTChapter
import code.model.reader.readerTContent
import code.model.reader.readerTOption
import code.model.reader.readerTStorys
import retrofit2.http.Body
import retrofit2.http.POST

interface TChapterContentService {
    //根据故事ID显示本故事的基本信息
    @POST("/TChapterContentCtrl/tStoryByStoryId")
    suspend fun tStoryByStoryId(@Body values: Map<String, Int>): ResultData<readerTStorys>

    //根据章节ID显示本章节的基本信息
    @POST("/TChapterContentCtrl/tChapterByChapterId")
    suspend fun tChapterByChapterId(@Body values: Map<String, Int>): ResultData<readerTChapter>


    //根据故事ID显示本故事的所有章节
    @POST("/TChapterContentCtrl/tChapterListByStoryId")
    suspend fun tChapterListByStoryId(@Body values: Map<String, Int>): ResultData<List<readerTChapter>>

//根据章节ID显示本章节的所有内容
    @POST("/TChapterContentCtrl/tContentListByChapterId")
    suspend fun tContentListByChapterId(@Body values: Map<String, Int>): ResultData<List<readerTContent>>

//    根据章节ID显示本章节的所有选项
    @POST("/TChapterContentCtrl/tOptionListByChapterId")
    suspend fun tOptionListByChapterId(@Body values: Map<String, Int>): ResultData<List<readerTOption>>

//    根据故事ID和章节名模糊查询章节
    @POST("/TChapterContentCtrl/tChapterByStoryIdAndChapterTitle")
    suspend fun tChapterByStoryIdAndChapterTitle(@Body values: Map<String, Int>): ResultData<readerTChapter>
}
