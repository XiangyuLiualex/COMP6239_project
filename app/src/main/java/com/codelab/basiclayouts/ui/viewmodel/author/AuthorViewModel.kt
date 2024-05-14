package com.codelab.basiclayouts.ui.viewmodel.author

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelab.basiclayouts.data.RetrofitInstance
import com.codelab.basiclayouts.model.reader.readerFavoriteAuthor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.codelab.basiclayouts.ui.uistate.author.*
import com.codelab.basiclayouts.ui.uistate.reader.ReaderFavouriteScreenUiState
import kotlinx.coroutines.launch
import kotlin.random.Random

class AuthorEditViewModel : ViewModel() {
    // 初始化一些示例数据
    private val content1 = ContentAU(contentId = 1, chapterId = 101, contentType = 1, contentData = "Welcome to the story.")
    private val content2 = ContentAU(contentId = 2, chapterId = 101, contentType = 2, contentData = "It was a dark and stormy night.")
    private val option1 = OptionAU(optionId = 1, optionName = "Go to the castle", chapterId = 101, nextChapterId = 102)
    private val option2 = OptionAU(optionId = 2, optionName = "Return home", chapterId = 101, nextChapterId = 103)
    private val chapter1 = ChapterAU(chapterId = 101, chapterTitle = "Chapter One", storyId = 201, contentList = listOf(content1, content2), optionList = listOf(option1, option2), isEnd = 0)
    private val chapter2 = ChapterAU(chapterId = 102, chapterTitle = "Chapter Two", storyId = 201, contentList = listOf(content1), optionList = listOf(), isEnd = 0)
    private val chapter3 = ChapterAU(chapterId = 103, chapterTitle = "Chapter Three", storyId = 201, contentList = listOf(content2), optionList = listOf(option2), isEnd = 0)
    private val story1 = StoryAU(storyId = 201, storyName = "An Adventure", storyDescription = "A thrilling quest through uncharted territories.", storyCategory = 2, chapterList = listOf(chapter1, chapter2, chapter3), isUsed = 2)
    private val story2 = StoryAU(storyId = 202, storyName = "Empty Story", storyDescription = "This is an empty story", storyCategory = 2, chapterList = listOf(), isUsed = 2)
    private val story3 = StoryAU(storyId = 203, storyName = "Good Story", storyDescription = "This is a good story", storyCategory = 3, chapterList = listOf(), isUsed = 1)
    private val category1 = CategoryAU(categoryId = 1, categoryName = "fantasy")
    private val category2 = CategoryAU(categoryId = 2, categoryName = "horror")
    private val category3 = CategoryAU(categoryId = 3, categoryName = "romance")


    private val _authorEditUiState = MutableStateFlow(
        AuthorEditUiState2(
            thisChapter = ChapterAU(),
            thisStory=StoryAU(),
            storyList = listOf(),
            categoryList = listOf(category1, category2,category3),
            authorId = 4
        )
    )


    fun getStory(storyId:Int) {
        viewModelScope.launch {
            try {
                // 创建一个 Map，包含读者ID
                val params = mapOf("storyId" to storyId)
                // 调用挂起函数
                val StoryResult = RetrofitInstance.tAuthorChapterContentService.tRootAuthorStoryByStoryId(params)
                // 更新状态
                _authorEditUiState.value = _authorEditUiState.value.copy(thisStory = StoryResult.data as StoryAU)

//                _uiState.value = ReaderFavouriteScreenUiState(authors = authorsResult.data as List<readerFavoriteAuthor>)
            } catch (e: Exception) {
                e.printStackTrace()
                // 在此处可以设置错误状态或采取其他行动
            }
        }
    }

    fun getStoryList(authorId:Int) {
        viewModelScope.launch {
            try {
                // 创建一个 Map，包含读者ID
                val params = mapOf("authorId" to authorId)
                // 调用挂起函数
                val StoryListResult = RetrofitInstance.tAuthorChapterContentService.tAuthorStorysByAuthorId(params)
                // 更新状态
                _authorEditUiState.value = _authorEditUiState.value.copy(storyList = StoryListResult.data as List<StoryAU>)

//                _uiState.value = ReaderFavouriteScreenUiState(authors = authorsResult.data as List<readerFavoriteAuthor>)
            } catch (e: Exception) {
                e.printStackTrace()
                // 在此处可以设置错误状态或采取其他行动
            }
        }
    }

    fun saveThisStory(story:StoryAU ){
        viewModelScope.launch {
            try {
                // 创建一个 Map，包含读者ID
//                val params = mapOf("authorId" to authorId)
                // 调用挂起函数
                val StoryListResult = RetrofitInstance.tAuthorChapterContentService.tAuthorUpdateStory(story)
                // 更新状态
//                _authorEditUiState.value = _authorEditUiState.value.copy(storyList = StoryListResult.data as List<StoryAU>)

//                _uiState.value = ReaderFavouriteScreenUiState(authors = authorsResult.data as List<readerFavoriteAuthor>)
            } catch (e: Exception) {
                e.printStackTrace()
                // 在此处可以设置错误状态或采取其他行动
            }
        }
    }


    init{
        getStoryList(4)
    }

    // Expose an immutable StateFlow
    val authorEditUiState = _authorEditUiState.asStateFlow()

    // 管理当前显示的屏幕

    private val _activeScreen = MutableStateFlow("AuthorMainScreen")
//    private val _activeScreen = MutableStateFlow("AuthorMainScreen")
    val activeScreen = _activeScreen.asStateFlow()

    fun setActiveScreen(screenName: String) {
        _activeScreen.value = screenName
    }

    // 添加章节
    fun addChapter(newChapter: ChapterAU) {
        val currentChapters = _authorEditUiState.value.thisStory.chapterList.toMutableList()
        currentChapters.add(newChapter)

        val updatedStory = _authorEditUiState.value.thisStory.copy(chapterList = currentChapters)
        _authorEditUiState.value = _authorEditUiState.value.copy(thisStory = updatedStory)
    }

    // 新增故事
    fun addStory(newStory: StoryAU) {
        val currentStories = _authorEditUiState.value.storyList.toMutableList()
        currentStories.add(newStory)

        _authorEditUiState.value = _authorEditUiState.value.copy(storyList = currentStories)
    }

    // 设置当前故事
    fun setThisStory(story: StoryAU) {
        _authorEditUiState.value = _authorEditUiState.value.copy(thisStory = story)
    }

    // 更新当前章节的内容和标题
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

    // 删除内容
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
        var newOptionId = Random.nextInt(1,100000)
        while (_authorEditUiState.value.thisChapter.optionList.any { it.optionId == newOptionId }) {
            newOptionId = Random.nextInt(1,100000)
        }
        return newOptionId
    }

    // 打印当前 UI 状态
    fun printAuthorEditUiState() {
        println(_authorEditUiState.value)
    }

    // 生成唯一内容 ID
    private fun generateRandomContentId(currentContents: List<ContentAU>): Int {
        var newContentId = Random.nextInt(1,100000)
        while (currentContents.any { it.contentId == newContentId }) {
            newContentId = Random.nextInt(1,100000)
        }
        return newContentId
    }

    // 设置当前章节
    fun setCurrentChapter(chapter: ChapterAU) {
        _authorEditUiState.value = _authorEditUiState.value.copy(thisChapter = chapter)
    }

    // 更新章节列表中的章节
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

    fun updateStoryInList() {
        val updatedStorys = _authorEditUiState.value.storyList.map { story ->
            if (story.storyId == _authorEditUiState.value.thisStory.storyId) {
                _authorEditUiState.value.thisStory
            } else {
                story
            }
        }
        _authorEditUiState.value = _authorEditUiState.value.copy(storyList = updatedStorys)
    }



    // 选择并编辑特定故事
    fun selectStory(story: StoryAU) {
        _authorEditUiState.value = _authorEditUiState.value.copy(thisStory = story)
    }
    fun updateChapterIsEnd(isEnd: Int) {
        // 获取当前章节的副本，并更新其 isEnd 属性
        val updatedChapter = authorEditUiState.value.thisChapter.copy(isEnd = isEnd)

        // 更新状态，将修改后的章节设置为当前章节
        _authorEditUiState.value = authorEditUiState.value.copy(
            thisChapter = updatedChapter
        )
    }


    fun publishStory() {
        // 将当前故事的 isUsed 设置为 1（发布状态）
        _authorEditUiState.value = _authorEditUiState.value.copy(
            thisStory = _authorEditUiState.value.thisStory.copy(isUsed = 1)
        )
        updateStoryInList()
    }

    fun unpublishStory() {
        // 将当前故事的 isUsed 设置为 2（草稿状态）
        _authorEditUiState.value = _authorEditUiState.value.copy(
            thisStory = _authorEditUiState.value.thisStory.copy(isUsed = 2)
        )
        updateStoryInList()
    }


}
