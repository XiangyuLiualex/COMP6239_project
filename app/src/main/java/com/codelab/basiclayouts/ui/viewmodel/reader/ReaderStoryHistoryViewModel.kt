package com.codelab.basiclayouts.ui.viewmodel.reader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelab.basiclayouts.data.RetrofitInstance
import com.codelab.basiclayouts.model.reader.readingPath
import com.codelab.basiclayouts.ui.uistate.reader.ReaderStoryHistoryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ReaderStoryHistoryViewModel  : ViewModel(){
    private val _historyState = MutableStateFlow(ReaderStoryHistoryUiState())
    val historyState: StateFlow<ReaderStoryHistoryUiState> = _historyState.asStateFlow()

    init {
        loadHistory(1,1)//替换为全局读者ID
    }

    fun loadHistory(readerId: Int, storyId: Int) {
        viewModelScope.launch {
            try {
                val tHistoryResult = RetrofitInstance.tHistoryService.getPathsByStoryId(
                    mapOf(
                        "readerId" to readerId,
                        "storyId" to storyId
                    )
                )
                if (tHistoryResult.code == 2000) {
                    _historyState.value = _historyState.value.copy(
                        readerId = readerId,
                        storyId = storyId,
                        readingPath  = tHistoryResult.data as List<readingPath>
                    )
                } else {
                    // 处理错误
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}