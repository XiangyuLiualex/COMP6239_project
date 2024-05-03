package com.codelab.basiclayouts.ui.viewmodel.author

import androidx.lifecycle.ViewModel
import com.codelab.basiclayouts.ui.model.author.AuthorEditUiState
import com.codelab.basiclayouts.ui.model.author.Option
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AuthorEditStoryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AuthorEditUiState())
    val uiState: StateFlow<AuthorEditUiState> = _uiState.asStateFlow()

    fun updateChapterName(name: String) {
        _uiState.update { it.copy(chapterName = name) }
    }

    fun addTextField() {
        _uiState.update { it.copy(textFields = it.textFields + "") }
    }

    fun updateTextField(index: Int, newText: String) {
        _uiState.update { currentState ->
            val updatedList = currentState.textFields.toMutableList().apply { this[index] = newText }
            currentState.copy(textFields = updatedList)
        }
    }

    fun removeTextField(index: Int) {
        _uiState.update { currentState ->
            val updatedList = currentState.textFields.toMutableList().apply { removeAt(index) }
            currentState.copy(textFields = updatedList)
        }
    }

    fun addOption(optionName: String, relatedChapterId: String) {
        val newOption = Option(optionName, relatedChapterId)
        _uiState.update { currentState ->
            currentState.copy(options = currentState.options + newOption)
        }
    }

    fun removeOption(optionName: String) {
        _uiState.update { currentState ->
            currentState.copy(options = currentState.options.filterNot { it.name == optionName })
        }
    }


    fun toggleEndChapter() {
        _uiState.update { it.copy(isTheEnd = !it.isTheEnd) }
    }

    fun toggleNavigationVisibility() {
        _uiState.update { it.copy(isNavigationVisible = !it.isNavigationVisible) }
    }
}
