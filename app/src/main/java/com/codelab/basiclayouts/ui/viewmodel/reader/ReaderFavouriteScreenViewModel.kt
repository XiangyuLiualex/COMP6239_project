package com.codelab.basiclayouts.ui.viewmodel.reader

import androidx.lifecycle.ViewModel
import com.codelab.basiclayouts.data.RetrofitInstance
import com.codelab.basiclayouts.model.reader.FavoriteAuthor
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
        tFavoriteAuthorList(1)
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
                _uiState.value = ReaderFavouriteScreenUiState(authors = authorsResult.data as List<FavoriteAuthor>)
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
                // 创建一个 Map，包含读者ID
                val params = mapOf("readerId" to readerId,"authorId" to authorId)
                // 调用挂起函数
                val delResult = RetrofitInstance.tFavoriteAuthorService.tFavoriteAuthorDel(params)
                // 更新状态(如删除后重新刷新列表)
            } catch (e: Exception) {
                e.printStackTrace()
                // 在此处可以设置错误状态或采取其他行动
            }
        }
    }
}