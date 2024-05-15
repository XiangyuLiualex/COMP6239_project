package code.ui.viewmodel.reader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelab.basiclayouts.R
import code.data.RetrofitInstance
import code.model.reader.readerTStorysForUiState
import code.ui.uistate.reader.StoryHomeScreenUiState
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
            try {var params = mutableMapOf ("storyId" to 1,"chapterId" to 1)//随便写的一个参数什么不重要

                val storiesResult = RetrofitInstance.tLibraryService.selectReaderStoryDetail(params)
                val stories = storiesResult.data as List<readerTStorysForUiState>

                _uiState.value = _uiState.value.copy(
                    readerId = R.integer.READERID,
                    readerTStorys = stories
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
