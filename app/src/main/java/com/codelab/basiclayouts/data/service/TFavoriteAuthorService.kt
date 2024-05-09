package com.codelab.basiclayouts.data.service

import com.codelab.basiclayouts.data.ResultData
import retrofit2.http.Body
import retrofit2.http.POST

// Retrofit Service 接口
interface TFavoriteAuthorService {
    /**
     * 根据读者ID查询其所喜欢的作者
     */
    @POST("/TFavoriteAuthorCtrl/tFavoriteAuthorList")
    suspend fun tFavoriteAuthorList(@Body values: Map<String, Any>): ResultData<Any>

    /**
     * 根据作者名模糊查询其所喜欢的作者
     */
    @POST("/TFavoriteAuthorCtrl/tFavoriteAuthorListByAuthorName")
    suspend fun tFavoriteAuthorListByAuthorName(@Body values: Map<String, Any>): ResultData<Any>

    /**
     * 添加喜欢的作者
     */
    @POST("/TFavoriteAuthorCtrl/tFavoriteAuthorInsert")
    suspend fun tFavoriteAuthorInsert(@Body values: Map<String, Any>): ResultData<Any>

    /**
     * 根据读者ID和作者ID删除某项喜欢的作者
     */
    @POST("/TFavoriteAuthorCtrl/tFavoriteAuthorDel")
    suspend fun tFavoriteAuthorDel(@Body values: Map<String, Any>): ResultData<Any>
}
