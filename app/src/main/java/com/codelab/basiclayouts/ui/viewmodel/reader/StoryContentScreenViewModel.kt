package com.codelab.basiclayouts.ui.viewmodel.reader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelab.basiclayouts.R
import com.codelab.basiclayouts.data.RetrofitInstance
import com.codelab.basiclayouts.model.reader.readerFavoriteAuthor
import com.codelab.basiclayouts.model.reader.readerStoryContent
import com.codelab.basiclayouts.model.reader.readerTChapter
import com.codelab.basiclayouts.model.reader.readerTOption
import com.codelab.basiclayouts.model.reader.readerTStorys
import com.codelab.basiclayouts.ui.screens.reader.ContentItem
import com.codelab.basiclayouts.ui.uistate.reader.ReaderFavouriteScreenUiState
import com.codelab.basiclayouts.ui.uistate.reader.StoryContentScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StoryContentScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(StoryContentScreenUiState(
        readerTStorys = readerTStorys(),
        readerTChapter = readerTChapter(),
        )
    )

    val uiState: StateFlow<StoryContentScreenUiState> = _uiState

    init {
        loadContent(1,1)
    }

    fun loadContent(storyId : Int,chapterId : Int) {
        viewModelScope.launch {
            try {
                // 创建一个 Map
                val params = mapOf("storyId" to storyId,"chapterId" to chapterId)
                // 章节详细信息
                val tStoryDetailResult = RetrofitInstance.tChapterContentService.tStoryByStoryId(params)
                val tChapterDetailResult = RetrofitInstance.tChapterContentService.tChapterByChapterId(params)
                val readerStoryContentListResult = RetrofitInstance.tChapterContentService.tContentListByChapterId(params)
                val readerTOptionList = RetrofitInstance.tChapterContentService.tOptionListByChapterId(params)
                // 更新状态
                _uiState.value = StoryContentScreenUiState(
                    readerTStorys = tStoryDetailResult.data as readerTStorys,
                    readerTChapter = tChapterDetailResult.data as readerTChapter,
                    readerStoryContentList = readerStoryContentListResult.data as List<readerStoryContent>,
                    readerTOptionList = readerTOptionList.data as List<readerTOption>
                )
            } catch (e: Exception) {
                e.printStackTrace()
                // 在此处可以设置错误状态或采取其他行动
            }
        }


    }
}