package com.codelab.basiclayouts.ui.viewmodel.reader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelab.basiclayouts.R
import com.codelab.basiclayouts.data.RetrofitInstance
import com.codelab.basiclayouts.model.reader.readerFavoriteAuthor
import com.codelab.basiclayouts.model.reader.readerTChapter
import com.codelab.basiclayouts.model.reader.readerTContent
import com.codelab.basiclayouts.model.reader.readerTOption
import com.codelab.basiclayouts.model.reader.readerTStorys
import com.codelab.basiclayouts.model.reader.readingPath
import com.codelab.basiclayouts.model.reader.readingPathItem
import com.codelab.basiclayouts.ui.screens.reader.ContentItem
import com.codelab.basiclayouts.ui.uistate.reader.ReaderFavouriteScreenUiState
import com.codelab.basiclayouts.ui.uistate.reader.StoryContentScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date

class StoryContentScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(StoryContentScreenUiState(
        readerTStorys = readerTStorys(),
        readerTChapter = readerTChapter(),
        )
    )
    val uiState: StateFlow<StoryContentScreenUiState> = _uiState.asStateFlow()


//    init {
//        loadContent(1,1,1,1)
//    }

    // 如果是第一次读的话，chapterId传0，就会自动读第一章
    fun loadContent(readerId:Int,storyId : Int,chapterId : Int,currentReadingPathId:Int) {
        viewModelScope.launch {
            try {
                // 创建一个 Map
                var params = mutableMapOf ("storyId" to storyId,"chapterId" to chapterId)
                // 章节详细信息
                val tStoryDetailResult = RetrofitInstance.tChapterContentService.tStoryByStoryId(params)
                if(chapterId <= 0){//chapterId传0，就会自动读第一章,查找第一章的id，找is_end=2的
                    var newchapterId : Int = 0//如果找不到第一张就不显示
                    val haveTChapterList = RetrofitInstance.tChapterContentService.tChapterListByStoryId(params);
                    val tchapterList = haveTChapterList.data as List<readerTChapter>
                    for(item in tchapterList){
                        if(item.isEnd==2){
                            newchapterId = item.chapterId
                        }
                    }
                    params["chapterId"] = newchapterId
                }
                //如果有chapterId就正常put
                val tChapterDetailResult = RetrofitInstance.tChapterContentService.tChapterByChapterId(params)
                val readerStoryContentListResult = RetrofitInstance.tChapterContentService.tContentListByChapterId(params)
                val readerTOptionList = RetrofitInstance.tChapterContentService.tOptionListByChapterId(params)
                // 更新状态
                // 如果页面之前有过阅读痕迹则就用这个头节点
                if(currentReadingPathId!=null && currentReadingPathId > 0){
                    _uiState.value = _uiState.value.copy(
                        readingPathId = currentReadingPathId,
                        readerTStorys = tStoryDetailResult.data as readerTStorys,
                        readerTChapter = tChapterDetailResult.data as readerTChapter,
                        readerTContentList = readerStoryContentListResult.data as List<readerTContent>,
                        readerTOptionList = readerTOptionList.data as List<readerTOption>
                    )
                }else{// 如果页面之前没有过阅读痕迹则就新建这个头节点和他的第一个项
                    val haveChapterTitle = tChapterDetailResult.data as readerTChapter
                    // 创建 readingPath 实例
                    val readingPath = readingPath(
                        startTime = null, // 开始时间
                        updateTime = null, // 更新时间
                        storyId = storyId, // 示例故事ID
                        startReadingPathItemId = -1, // 起始阅读路径项ID,先假设不使用，后面看看需不需要更新
                        readerId = readerId, // 示例读者ID
                        readingPathItemList = listOf() // 阅读路径项列表
                    )
                    //新建一个头结点
                    val haveTReadingPathId = RetrofitInstance.tHistoryService.createReadingPath(readingPath)
                    val readingPathItem = readingPathItem(
                        chapterId = chapterId,
                        chapterName = haveChapterTitle.chapterTitle, // 示例章节名称
                        readingTimes = null, // 当前时间
                        nextReadingPathId = -1, // 示例下一阅读路径ID，先假设不使用，后面看看需不需要更新(好像确实没有使用)
                        readingTime = null, // 当前时间
                        order = chapterId, // 示例顺序，现在是假设章节是按顺序的，实际还是按时间排序吧
                        tReadingPathId = haveTReadingPathId.data as Int // 示例所属章节路径记录头节点ID
                    )
                    //新建一个属于这个头结点的Item
                    RetrofitInstance.tHistoryService.insertPathItem(readingPathItem);
                    //最后更新新建的ID到UiState中
                    _uiState.value = _uiState.value.copy(
                        readingPathId = haveTReadingPathId.data as Int,
                        readerTStorys = tStoryDetailResult.data as readerTStorys,
                        readerTChapter = tChapterDetailResult.data as readerTChapter,
                        readerTContentList = readerStoryContentListResult.data as List<readerTContent>,
                        readerTOptionList = readerTOptionList.data as List<readerTOption>
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // 在此处可以设置错误状态或采取其他行动
            }
        }
    }

        fun loadNextChapterByOption(storyId : Int,chapterId : Int) {
            viewModelScope.launch {
                try {
                    // 创建一个 Map
                    val params = mapOf("storyId" to storyId, "chapterId" to chapterId)
                    // 章节详细信息
                    val tStoryDetailResult =
                        RetrofitInstance.tChapterContentService.tStoryByStoryId(params)
                    val tChapterDetailResult =
                        RetrofitInstance.tChapterContentService.tChapterByChapterId(params)
                    val readerStoryContentListResult =
                        RetrofitInstance.tChapterContentService.tContentListByChapterId(params)
                    val readerTOptionList =
                        RetrofitInstance.tChapterContentService.tOptionListByChapterId(params)
                    // 更新状态
                    _uiState.value = _uiState.value.copy(
                        chapterId = chapterId,//与load相比多加一个更新章节ID
                        readerTStorys = tStoryDetailResult.data as readerTStorys,
                        readerTChapter = tChapterDetailResult.data as readerTChapter,
                        readerTContentList = readerStoryContentListResult.data as List<readerTContent>,
                        readerTOptionList = readerTOptionList.data as List<readerTOption>
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                    // 在此处可以设置错误状态或采取其他行动
                }
            }
    }

    fun addFavoriteAuthor() {
        viewModelScope.launch {
            try {
                // 创建一个 Map
                val params = mapOf("storyId" to _uiState.value.storyId, "readerId" to _uiState.value.readerId,"isUsed" to 1)
                // 章节详细信息
                val tStoryDetailResult =
                    RetrofitInstance.tFavoriteAuthorService.tFavoriteAuthorInsertByStoryId(params)//根据故事ID找到读者然后userID添加喜欢作者
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addToLibrary() {
        viewModelScope.launch {
            try {
                // 创建一个 Map
                val params = mapOf("storyId" to _uiState.value.storyId, "readerId" to _uiState.value.readerId,"isUsed" to 1)
                // 章节详细信息
                val tStoryDetailResult =
                    RetrofitInstance.tLibraryService.tLibraryInsert(params)//根据故事ID喜欢故事
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }
}