package com.codelab.basiclayouts.ui.uistate.author

data class AuthorEditUiState2(
    val thisChapter:ChapterAU
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
    val chapterId:Int,
    val chapterTitle:String,
    val storyId:Int,
    val contentList:List<ContentAU>,
    val optionList:List<OptionAU>,
    val isEnd:Int
)



