package com.codelab.basiclayouts.data.service

import com.codelab.basiclayouts.data.ResultData
import com.codelab.basiclayouts.model.reader.readerStoryHistory
import com.codelab.basiclayouts.model.reader.readingPath
import com.codelab.basiclayouts.model.reader.readingPathItem
import retrofit2.http.Body
import retrofit2.http.POST

interface THistoryService {

    @POST("/THistoryCtrl/insertReaderHistory")
    suspend fun insertReaderHistory(@Body values: readerStoryHistory): ResultData<readerStoryHistory> // 无返回类型

    @POST("/THistoryCtrl/updateReaderHistory")
    suspend fun updateReaderHistory(@Body values: readerStoryHistory): ResultData<readerStoryHistory>

    @POST("/THistoryCtrl/findHistoryByReaderId")
    suspend fun findHistoryByReaderId(@Body values: Map<String, Int>): ResultData<List<readerStoryHistory>>

    @POST("/THistoryCtrl/findHistoryByStoryId")
    suspend fun findHistoryByStoryId(@Body values: Map<String, Int>): ResultData<List<readerStoryHistory>>

    @POST("/THistoryCtrl/createReadingPath")
    suspend fun createReadingPath(@Body values: readingPath): ResultData<Int>

    @POST("/THistoryCtrl/updateReadingPath")
    suspend fun updateReadingPath(@Body values: readingPath): ResultData<readingPath>

    @POST("/THistoryCtrl/getReadingPathById")
    suspend fun getReadingPathById(@Body values: Map<String, Int>): ResultData<readingPath>

    @POST("/THistoryCtrl/getPathsByStoryId")
    suspend fun getPathsByStoryId(@Body values: Map<String, Int>): ResultData<List<readingPath>>

    @POST("/THistoryCtrl/insertPathItem")
    suspend fun insertPathItem(@Body values: readingPathItem): ResultData<Int>

    @POST("/THistoryCtrl/updatePathItem")
    suspend fun updatePathItem(@Body values: readingPathItem): ResultData<readingPathItem>

    @POST("/THistoryCtrl/getPathItemsByChapterId")
    suspend fun getPathItemsByChapterId(@Body values: Map<String, Int>): ResultData<List<readingPathItem>>

    @POST("/THistoryCtrl/getPathItemById")
    suspend fun getPathItemById(@Body values: Map<String, Int>): ResultData<readingPathItem>
}