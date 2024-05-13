package com.codelab.basiclayouts.ui.uistate.author

data class AuthorEditUiState2(
    val thisChapter:ChapterAU,
    val thisStory:StoryAU,
    val storyList:List<StoryAU>,
    val categoryList:List<CategoryAU>,
    val authorId:Int
)

data class OptionAU(
    val optionId:Int,
    val optionName: String,
    val chapterId: Int,
    val nextChapterId:Int
)

data class ContentAU(
    val contentId:Int,
    val chapterId:Int,
    val contentType:Int,
    val contentData:String
)

data class ChapterAU(
    val chapterId:Int=-1,
    val chapterTitle:String="",
    val storyId:Int=-1,
    val contentList:List<ContentAU> = listOf(),
    val optionList:List<OptionAU> = listOf(),
    val isEnd:Int =-1
)

data class StoryAU(
    val storyId:Int=-1,
    val storyName:String="",
    val storyDescription: String="",
    val storyCategory: Int=-1,
    val chapterList: List<ChapterAU> =listOf(),
    val authorId: Int=-1,
    val isUsed:Int=2
)

data class CategoryAU(
    val categoryId:Int,
    val categoryName:String
)





