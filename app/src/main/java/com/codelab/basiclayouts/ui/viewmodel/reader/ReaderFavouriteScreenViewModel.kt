package com.codelab.basiclayouts.ui.viewmodel.reader

import androidx.lifecycle.ViewModel
import com.codelab.basiclayouts.model.Author
import com.codelab.basiclayouts.model.reader.ReaderFavouriteScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ReaderFavouriteScreenViewModel  : ViewModel() {

    private val _uiState = MutableStateFlow(ReaderFavouriteScreenUiState())
    val uiState: StateFlow<ReaderFavouriteScreenUiState> = _uiState.asStateFlow()

    // 初始化时可以设置一些测试数据，或者通过 HTTP 请求获取数据
    init {
        loadAuthors()
    }

    // 模拟数据加载或从远程获取数据
    private fun loadAuthors() {
        val sampleAuthors = listOf(
            Author("1", "张三", "https://example.com/zhang3.png", "《江城》等"),
            Author("2", "李四", "https://example.com/li4.png", "《无名之辈》等")
        )
        _uiState.value = ReaderFavouriteScreenUiState(authors = sampleAuthors)
    }
}