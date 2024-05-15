package code.data

import android.content.Context
import code.data.service.TFavoriteAuthorService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


import com.codelab.basiclayouts.R
import code.data.service.TAuthorChapterContentService
import code.data.service.TChapterContentService
import code.data.service.THistoryService
import code.data.service.TLibraryService
import code.data.service.TUserService

object RetrofitInstance {
    private lateinit var retrofit: Retrofit

    lateinit var tChapterContentService: TChapterContentService
    lateinit var tFavoriteAuthorService: TFavoriteAuthorService
    lateinit var tLibraryService: TLibraryService
    lateinit var tAuthorChapterContentService: TAuthorChapterContentService
    lateinit var tHistoryService: THistoryService
    lateinit var tUserService : TUserService

    // 动态获取 Base URL
    private fun getBaseUrl(context: Context): String {
        return context.resources.getString(R.string.api_base_url)
    }

    // 初始化 Retrofit 实例
    fun initialize(context: Context) {
        retrofit = Retrofit.Builder()
            .baseUrl(getBaseUrl(context))  // 使用动态获取的 Base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        initServices()
    }

    // 初始化所有服务
    private fun initServices() {
        tChapterContentService = retrofit.create(TChapterContentService::class.java)
        tFavoriteAuthorService = retrofit.create(TFavoriteAuthorService::class.java)
        tLibraryService = retrofit.create(TLibraryService::class.java)
        tAuthorChapterContentService = retrofit.create(TAuthorChapterContentService::class.java)
        tHistoryService = retrofit.create(THistoryService::class.java)
        tUserService = retrofit.create(TUserService::class.java)
    }
}
