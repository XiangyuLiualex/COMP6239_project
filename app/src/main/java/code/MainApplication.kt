package code

import android.app.Application
import code.data.RetrofitInstance
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        // 初始化 RetrofitInstance
        RetrofitInstance.initialize(this)
    }
}