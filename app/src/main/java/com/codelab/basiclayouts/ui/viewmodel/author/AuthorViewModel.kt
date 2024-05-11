package com.codelab.basiclayouts.ui.viewmodel.author

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.codelab.basiclayouts.ui.uistate.author.*
import kotlin.random.Random

class AuthorEditViewModel : ViewModel() {
    private val content1 = ContentAU(contentId = 1, chapterId = 101, contentType = 1, contentData = "Welcome to the story.")
    private val content2 = ContentAU(contentId = 2, chapterId = 101, contentType = 2, contentData = "It was a dark and stormy night.")
    private val option1 = OptionAU(optionId = 1, optionName = "Go to the castle", chapterId = 101, nextChapterId = 102)
    private val option2 = OptionAU(optionId = 2, optionName = "Return home", chapterId = 101, nextChapterId = 103)
    private val chapter1 = ChapterAU(chapterId = 101, chapterTitle = "Chapter One", storyId = 201, contentList = listOf(content1, content2), optionList = listOf(option1, option2), isEnd = 0)
    private val chapter2 = ChapterAU(chapterId = 102, chapterTitle = "Chapter Two", storyId = 201, contentList = listOf(content1), optionList = listOf(), isEnd = 0)
    private val chapter3 = ChapterAU(chapterId = 103, chapterTitle = "Chapter Three", storyId = 201, contentList = listOf(content2), optionList = listOf(option2), isEnd = 0)
    private val story1 = StoryAU(storyId = 201, storyName = "An Adventure", storyDescription = "A thrilling quest through uncharted territories.", storyCategory = "Adventure", chapterList = listOf(chapter1, chapter2, chapter3))

    private val _authorEditUiState = MutableStateFlow(
        AuthorEditUiState2(
            thisChapter = chapter1,
            thisStory = story1
        )
    )

    // Expose an immutable StateFlow
    val authorEditUiState = _authorEditUiState.asStateFlow()
    // 添加状态来跟踪当前活跃的屏幕
    private val _activeScreen = MutableStateFlow("StoryEditScreen") // 默认为 StoryEditScreen
    val activeScreen = _activeScreen.asStateFlow()

    fun setActiveScreen(screenName: String) {
        _activeScreen.value = screenName
    }

    // 新增章节
    fun addChapter(newChapter: ChapterAU) {
        val currentChapters = _authorEditUiState.value.thisStory.chapterList.toMutableList()
        currentChapters.add(newChapter)

        val updatedStory = _authorEditUiState.value.thisStory.copy(chapterList = currentChapters)
        _authorEditUiState.value = _authorEditUiState.value.copy(thisStory = updatedStory)
    }

    // 更新章节标题
    fun updateChapterTitle(newTitle: String) {
        val updatedChapter = _authorEditUiState.value.thisChapter.copy(chapterTitle = newTitle)
        _authorEditUiState.value = _authorEditUiState.value.copy(thisChapter = updatedChapter)
    }

    // 更新内容
    fun updateContent(contentId: Int, newContentData: String) {
        val updatedContents = _authorEditUiState.value.thisChapter.contentList.map { content ->
            if (content.contentId == contentId) content.copy(contentData = newContentData) else content
        }
        val updatedChapter = _authorEditUiState.value.thisChapter.copy(contentList = updatedContents)
        _authorEditUiState.value = _authorEditUiState.value.copy(thisChapter = updatedChapter)
    }

    // 添加新的内容
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

    // 删除指定内容
    fun removeContent(contentId: Int) {
        val updatedContents = _authorEditUiState.value.thisChapter.contentList.filter { it.contentId != contentId }
        val updatedChapter = _authorEditUiState.value.thisChapter.copy(contentList = updatedContents)
        _authorEditUiState.value = _authorEditUiState.value.copy(thisChapter = updatedChapter)
    }

    // 删除选项
    fun removeOption(optionId: Int) {
        val updatedOptions = _authorEditUiState.value.thisChapter.optionList.filter { it.optionId != optionId }
        val updatedChapter = _authorEditUiState.value.thisChapter.copy(optionList = updatedOptions)
        _authorEditUiState.value = _authorEditUiState.value.copy(thisChapter = updatedChapter)
    }

    // 添加新选项
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

    // 生成唯一选项 ID
    private fun generateRandomOptionId(): Int {
        var newOptionId = Random.nextInt()
        while (_authorEditUiState.value.thisChapter.optionList.any { it.optionId == newOptionId }) {
            newOptionId = Random.nextInt()
        }
        return newOptionId
    }

    // 打印当前的 UI 状态
    fun printAuthorEditUiState() {
        println(_authorEditUiState.value)
    }

    // 生成唯一内容 ID
    private fun generateRandomContentId(currentContents: List<ContentAU>): Int {
        var newContentId = Random.nextInt()
        while (currentContents.any { it.contentId == newContentId }) {
            newContentId = Random.nextInt()
        }
        return newContentId
    }
    fun setCurrentChapter(chapter: ChapterAU) {
        _authorEditUiState.value = _authorEditUiState.value.copy(thisChapter = chapter)
    }

    fun updateChapterInList() {
        val updatedChapters = _authorEditUiState.value.thisStory.chapterList.map { chapter ->
            if (chapter.chapterId == _authorEditUiState.value.thisChapter.chapterId) {
                _authorEditUiState.value.thisChapter
            } else {
                chapter
            }
        }
        val updatedStory = _authorEditUiState.value.thisStory.copy(chapterList = updatedChapters)
        _authorEditUiState.value = _authorEditUiState.value.copy(thisStory = updatedStory)
    }
}
