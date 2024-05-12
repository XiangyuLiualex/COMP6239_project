package com.codelab.basiclayouts.data.service

import com.codelab.basiclayouts.data.ResultData
import com.codelab.basiclayouts.model.reader.readerTStorys
import com.codelab.basiclayouts.model.reader.readerTStorysForUiState
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.Header

interface TLibraryService {


    /**
     * 根据读者ID展示图书馆
     */
    @POST("/TLibraryCtrl/tLibraryList")
    suspend fun tLibraryList(@Body values: Map<String, Int>): ResultData<List<readerTStorys>>

    /**
     * 根据读者ID展示图书馆
     */
    @POST("/TLibraryCtrl/tLibraryListReaderStoryForUiState")
    suspend fun tLibraryListReaderStoryForUiState(@Body values: Map<String, Int>): ResultData<List<readerTStorysForUiState>>

    /**
     * 根据图书馆保存的读者和图书ID显示详情，暂时不用
     */
    @POST("/TLibraryCtrl/tLibraryInfo")
    suspend fun tLibraryInfo(@Body values: Map<String, Int>): ResultData<readerTStorys>

    /**
     * 向图书馆内添加新图书
     */
    @POST("/TLibraryCtrl/tLibraryInsert")
    suspend fun tLibraryInsert(@Body values: Map<String, Int>): ResultData<Any>

    /**
     * 根据用户ID和图书ID删除图书馆的书藉
     */
    @POST("/TLibraryCtrl/tLibraryDel")
    suspend fun tLibraryDel(@Body values: Map<String, Int>): ResultData<Any>
}
