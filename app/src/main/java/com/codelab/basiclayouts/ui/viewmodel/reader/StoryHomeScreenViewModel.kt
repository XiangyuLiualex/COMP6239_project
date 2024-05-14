package com.codelab.basiclayouts.ui.viewmodel.reader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelab.basiclayouts.data.RetrofitInstance
import com.codelab.basiclayouts.model.reader.readerTStorysForUiState
import com.codelab.basiclayouts.ui.uistate.reader.StoryHomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StoryHomeScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(StoryHomeScreenUiState())
    val uiState: StateFlow<StoryHomeScreenUiState> = _uiState.asStateFlow()

    init {
        loadStories()
    }

    public fun loadStories() {
        viewModelScope.launch {
            try {
                val storiesResult = RetrofitInstance.tLibraryService.selectReaderStoryDetail()
                val stories = storiesResult.data as List<readerTStorysForUiState>

                _uiState.value = _uiState.value.copy(
                    readerTStorys = stories
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
