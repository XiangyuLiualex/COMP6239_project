package com.codelab.basiclayouts.data

import android.content.Context
import com.codelab.basiclayouts.data.service.TFavoriteAuthorService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// RetrofitInstance.kt
//object RetrofitInstance {
//    private val retrofit = Retrofit.Builder()
//        .baseUrl("http://10.14.153.29:8443/") // 我的本地springboothttp://10.14.153.180:8443/
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    //读者喜欢的作者管理API接口
//    val tFavoriteAuthorService: TFavoriteAuthorService by lazy {
//        retrofit.create(TFavoriteAuthorService::class.java)
//    }
//}


import com.codelab.basiclayouts.R
object RetrofitInstance {
    // 动态获取 Base URL
    private fun getBaseUrl(context: Context): String {
        return context.resources.getString(R.string.api_base_url)
    }

    // Retrofit 实例初始化需要一个 Context 参数
    fun initialize(context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(getBaseUrl(context)) // 使用动态获取的 Base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // 延迟初始化 TFavoriteAuthorService
    lateinit var tFavoriteAuthorService: TFavoriteAuthorService

    // 提供一个方法用于初始化服务
    fun initService(context: Context) {
        val retrofit = initialize(context)
        tFavoriteAuthorService = retrofit.create(TFavoriteAuthorService::class.java)
    }
}
