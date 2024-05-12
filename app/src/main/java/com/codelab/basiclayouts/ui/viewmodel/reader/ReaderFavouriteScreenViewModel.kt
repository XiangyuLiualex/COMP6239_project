package com.codelab.basiclayouts.ui.viewmodel.reader

import androidx.lifecycle.ViewModel
import com.codelab.basiclayouts.data.RetrofitInstance
import com.codelab.basiclayouts.model.reader.readerFavoriteAuthor
import com.codelab.basiclayouts.ui.uistate.reader.ReaderFavouriteScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ReaderFavouriteScreenViewModel  : ViewModel() {

    private val _uiState = MutableStateFlow(ReaderFavouriteScreenUiState())
    val uiState: StateFlow<ReaderFavouriteScreenUiState> = _uiState.asStateFlow()

    // 初始化时可以设置一些测试数据，或者通过 HTTP 请求获取数据
    init {
        tFavoriteAuthorList(1)//替换为全局读者ID
    }

    // 通过协程从远程获取数据，读者本人的ID
    fun tFavoriteAuthorList(readerId: Int) {
        viewModelScope.launch {
            try {
                // 创建一个 Map，包含读者ID
                val params = mapOf("readerId" to readerId)
                // 调用挂起函数
                val authorsResult = RetrofitInstance.tFavoriteAuthorService.tFavoriteAuthorList(params)
                // 更新状态
                _uiState.value = ReaderFavouriteScreenUiState(authors = authorsResult.data as List<readerFavoriteAuthor>)
            } catch (e: Exception) {
                e.printStackTrace()
                // 在此处可以设置错误状态或采取其他行动
            }
        }
    }


    // 通过协程，读者本人的ID，和对应作者的ID，删除对应的中间表项
    fun tFavoriteAuthorDel(readerId: Int, authorId: Int) {
        viewModelScope.launch {
            try {
                // 创建一个 Map，包含读者ID和作者ID
                val params = mapOf("readerId" to readerId, "authorId" to authorId)
                // 调用挂起函数进行删除操作
                val delResult = RetrofitInstance.tFavoriteAuthorService.tFavoriteAuthorDel(params)
                // 判断删除是否成功，这里需要依据delResult中的具体内容判断
                if (delResult.code == 2000) {
                    // 删除成功，重新加载列表
                    tFavoriteAuthorList(readerId)
                } else {
                    // 可以在这里处理删除失败的情况，例如更新 UI 状态以显示错误信息
                    // 这里是假设_delResult中有code属性，你可能需要根据你的API响应实际调整
                    println("删除失败: ${delResult.msg}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // 在此处可以设置错误状态或采取其他行动
                println("删除操作异常: ${e.message}")
            }
        }
    }


    //根据收藏的作者模糊查询
    fun searchAuthors(query: String) {
        // 搜索逻辑，这里可以是过滤本地列表或发起新的网络请求
        viewModelScope.launch {
            try {
                if (query.isNotEmpty()){
                    // 创建一个 Map，包含作者名
                    val params = mapOf("AuthorName" to query,"readerId" to "1")
                    val authorsResult = RetrofitInstance.tFavoriteAuthorService.tFavoriteAuthorListByAuthorName(params)
                    if (authorsResult.code == 2000) {
                        // 更新状态
                        _uiState.value = ReaderFavouriteScreenUiState(authors = authorsResult.data as List<readerFavoriteAuthor>)
                    } else {
                        println("查询失败: ${authorsResult.msg}")
                    }
                } else {
                    tFavoriteAuthorList(1)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // 在此处可以设置错误状态或采取其他行动
                println("查询操作异常: ${e.message}")
            }
        }
    }

}