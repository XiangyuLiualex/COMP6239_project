package com.codelab.basiclayouts

import android.app.Application
import com.codelab.basiclayouts.data.RetrofitInstance
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        // 初始化 RetrofitInstance
        RetrofitInstance.initialize(this)
    }
}