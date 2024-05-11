package com.codelab.basiclayouts.ui.viewmodel.author

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.codelab.basiclayouts.ui.uistate.author.AuthorEditUiState2
import com.codelab.basiclayouts.ui.uistate.author.ChapterAU
import com.codelab.basiclayouts.ui.uistate.author.ContentAU
import com.codelab.basiclayouts.ui.uistate.author.OptionAU
import com.codelab.basiclayouts.ui.uistate.author.StoryAU
import kotlin.random.Random

class AuthorEditViewModel : ViewModel() {
    private val content1 = ContentAU(contentId = 1, chapterId = 101, contentType = 1, contentData = "Welcome to the story.")
    private val content2 = ContentAU(contentId = 2, chapterId = 101, contentType = 2, contentData = "It was a dark and stormy night.")
    private val option1 = OptionAU(optionId = 1, optionName = "Go to the castle", chapterId = 101, nextChapterId = 102)
    private val option2 = OptionAU(optionId = 2, optionName = "Return home", chapterId = 101, nextChapterId = 103)
    private val chapter1 = ChapterAU(chapterId = 101, chapterTitle = "Chapter One", storyId = 201, contentList = listOf(content1, content2), optionList = listOf(option1, option2), isEnd = 0)
    private val chapter2 = ChapterAU(chapterId = 102, chapterTitle = "Chapter Two", storyId = 201, contentList = listOf(content1), optionList = listOf(), isEnd = 1)
    private val chapter3 = ChapterAU(chapterId = 103, chapterTitle = "Chapter Three", storyId = 201, contentList = listOf(content2), optionList = listOf(option2), isEnd = 1)
    private val story1 = StoryAU(storyId = 201, storyName = "An Adventure", storyDescription = "A thrilling quest through uncharted territories.", storyCategory = "Adventure", chapterList = listOf(chapter1, chapter2,chapter3))

    private val _authorEditUiState = MutableStateFlow(
        AuthorEditUiState2(
            thisChapter = chapter1, // Set the initial chapter to chapter1
            thisStory = story1      // Set the initial story to story1
        )
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

    fun removeOption(optionId: Int) {
        val updatedOptions = _authorEditUiState.value.thisChapter.optionList.filter { it.optionId != optionId }
        val updatedChapter = _authorEditUiState.value.thisChapter.copy(optionList = updatedOptions)
        _authorEditUiState.value = _authorEditUiState.value.copy(thisChapter = updatedChapter)
    }

    fun addNewOption(optionName: String, nextChapterId: Int) {
        val newOption = OptionAU(
            optionId = generateRandomOptionId(),
            optionName = optionName,
            chapterId = _authorEditUiState.value.thisChapter.chapterId,
            nextChapterId = nextChapterId
        )
        val updatedOptions = _authorEditUiState.value.thisChapter.optionList.toMutableList().apply { add(newOption) }
        val updatedChapter = _authorEditUiState.value.thisChapter.copy(optionList = updatedOptions)
        _authorEditUiState.value = _authorEditUiState.value.copy(thisChapter = updatedChapter)
    }

    private fun generateRandomOptionId(): Int {
        var newOptionId = Random.nextInt()
        while (_authorEditUiState.value.thisChapter.optionList.any { it.optionId == newOptionId }) {
            newOptionId = Random.nextInt()
        }
        return newOptionId
    }


    fun printAuthorEditUiState() {
        println(_authorEditUiState.value)
    }
}
