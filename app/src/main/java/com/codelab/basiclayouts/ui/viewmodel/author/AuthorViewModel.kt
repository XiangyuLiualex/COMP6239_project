package com.codelab.basiclayouts.ui.viewmodel.author

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.codelab.basiclayouts.ui.uistate.author.AuthorEditUiState2
import com.codelab.basiclayouts.ui.uistate.author.ChapterAU
import com.codelab.basiclayouts.ui.uistate.author.ContentAU
import com.codelab.basiclayouts.ui.uistate.author.OptionAU
import kotlin.random.Random

class AuthorEditViewModel : ViewModel() {
    private val _authorEditUiState = MutableStateFlow(
        AuthorEditUiState2(
            thisChapter = ChapterAU(
                chapterId = 1,
                chapterTitle = "Chapter 1: The Adventure Begins",
                storyId = 101,
                contentList = listOf(
                    ContentAU(1, 1, 0, "Once upon a time..."),
                    ContentAU(2, 1, 1, "Our hero encounters a mysterious stranger.")
                ),
                optionList = listOf(
                    OptionAU(1, "Choose to follow the stranger", 1, 2),
                    OptionAU(2, "Ignore the stranger and continue", 1, 3)
                ),
                isEnd = 0
            )
        )
//        AuthorEditUiState2(
//            thisChapter = ChapterAU(
//                chapterId = 1,
//                chapterTitle = "",
//                storyId = 101,
//                contentList = listOf(),
//                optionList = listOf(),
//                isEnd = 0
//            )
//        )
    )

    // Expose an immutable StateFlow
    val authorEditUiState = _authorEditUiState.asStateFlow()

    fun updateChapterTitle(newTitle: String) {
        val updatedChapter = _authorEditUiState.value.thisChapter.copy(chapterTitle = newTitle)
        _authorEditUiState.value = _authorEditUiState.value.copy(thisChapter = updatedChapter)
    }

    fun updateContent(contentId: Int, newContentData: String) {
        val updatedContents = _authorEditUiState.value.thisChapter.contentList.map { content ->
            if (content.contentId == contentId) content.copy(contentData = newContentData) else content
        }
        val updatedChapter = _authorEditUiState.value.thisChapter.copy(contentList = updatedContents)
        _authorEditUiState.value = _authorEditUiState.value.copy(thisChapter = updatedChapter)
    }

    fun addNewContent() {
        val currentContents = _authorEditUiState.value.thisChapter.contentList
        val newContentId = generateRandomContentId(currentContents)
        val newContent = ContentAU(
            contentId = newContentId,
            chapterId = _authorEditUiState.value.thisChapter.chapterId,
            contentType = 0,
            contentData = ""
        )
        val updatedContents = currentContents.toMutableList().apply { add(newContent) }
        val updatedChapter = _authorEditUiState.value.thisChapter.copy(contentList = updatedContents)
        _authorEditUiState.value = _authorEditUiState.value.copy(thisChapter = updatedChapter)
    }

    private fun generateRandomContentId(currentContents: List<ContentAU>): Int {
        var newContentId = Random.nextInt()
        // 确保生成的 contentId 在当前内容列表中唯一
        while (currentContents.any { it.contentId == newContentId }) {
            newContentId = Random.nextInt()
        }
        return newContentId
    }
    fun removeContent(contentId: Int) {
        val updatedContents = _authorEditUiState.value.thisChapter.contentList.filter { it.contentId != contentId }
        val updatedChapter = _authorEditUiState.value.thisChapter.copy(contentList = updatedContents)
        _authorEditUiState.value = _authorEditUiState.value.copy(thisChapter = updatedChapter)
    }

    fun printAuthorEditUiState() {
        println(_authorEditUiState.value)
    }
}
