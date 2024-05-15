package code.ui.viewmodel.reader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelab.basiclayouts.R
import code.data.RetrofitInstance
import code.model.reader.readerTStorysForUiState
import code.ui.uistate.reader.ReaderLibraryScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ReaderLibraryScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ReaderLibraryScreenUiState())
    val uiState: StateFlow<ReaderLibraryScreenUiState> = _uiState.asStateFlow()

    init {
        _uiState.value.copy(
            readerId = R.integer.READERID
        )
        loadStories(_uiState.value.readerId)
    }

    fun loadStories(readerId: Int) {
        viewModelScope.launch {
            try {
                val params = mapOf("readerId" to readerId)
                val storysResult = RetrofitInstance.tLibraryService.tLibraryListReaderStoryForUiState(params)
                val storys = storysResult.data as List<readerTStorysForUiState>

                _uiState.value = _uiState.value.copy(
                    readerTStorys = storys
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun toggleFavorite(storyId: Int, readerId: Int) {
        viewModelScope.launch {
            try {
                val params = mapOf("readerId" to readerId, "storyId" to storyId)
                val delResult = RetrofitInstance.tLibraryService.tLibraryDel(params)
                if (delResult.code == 2000) {
                    // 从 UI 状态中移除取消收藏的故事
                    _uiState.value = _uiState.value.copy(
                        readerTStorys = _uiState.value.readerTStorys.filterNot { it.storyId == storyId }
                    )
                }
                loadStories(_uiState.value.readerId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
