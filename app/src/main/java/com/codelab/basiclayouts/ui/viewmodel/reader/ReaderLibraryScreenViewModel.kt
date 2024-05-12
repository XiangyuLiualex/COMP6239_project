package com.codelab.basiclayouts.ui.viewmodel.reader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelab.basiclayouts.data.RetrofitInstance
import com.codelab.basiclayouts.model.reader.readerFavoriteAuthor
import com.codelab.basiclayouts.model.reader.readerTStorys
import com.codelab.basiclayouts.model.reader.readerTStorysForUiState
import com.codelab.basiclayouts.ui.uistate.reader.ReaderFavouriteScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReaderLibraryScreenViewModel  : ViewModel() {
    private val _uiState = MutableStateFlow<List<readerTStorysForUiState>>(emptyList())
    val uiState: StateFlow<List<readerTStorysForUiState>> = _uiState

    init {
        loadStories()
    }


    //暂时还不行,有点麻烦
    private fun loadStories() {
        viewModelScope.launch {
            try {
                val params = mapOf("readerId" to 1)
                // 调用挂起函数
                val storysResult = RetrofitInstance.tLibraryService.tLibraryList(params)
                val storys = storysResult.data as List<readerTStorys>//存储剧本的基本信息还要从其他表查找信息

                val readerStorysForUiStateResult = RetrofitInstance.tLibraryService.tLibraryListReaderStoryForUiState(params)
                val readerStorysForUiState = readerStorysForUiStateResult.data as List<readerTStorysForUiState>

                // 合并数据
                val showData = readerStorysForUiState.mapNotNull { uiStateItem ->
                    storys.find { it.storyId == uiStateItem.storyId }?.let { matchingStory ->
                        uiStateItem.copy(
                            storyId = matchingStory.storyId,
                            storyName = matchingStory.storyName,
                            storyDescription = matchingStory.storyDescription,
                            storyTrends = matchingStory.storyTrends,
                            storyCoverUrl = matchingStory.storyCoverUrl
                        )
                    }
                }

                // 更新状态
                _uiState.value = showData

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun toggleFavorite(storyId: Int) {
        // 实现收藏和取消收藏逻辑
    }
}