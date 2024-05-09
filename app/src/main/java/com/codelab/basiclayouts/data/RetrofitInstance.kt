package com.codelab.basiclayouts.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// RetrofitInstance.kt
object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/") // 替换为你的后端地址,http://10.0.2.2:8080/ 表示假设你的springboot运行在本地
        .addConverterFactory(GsonConverterFactory.create())
        .build()

//定义若干调取HTTP的接口
//    val userService: UserService by lazy {
//        retrofit.create(UserService::class.java)
//    }
}