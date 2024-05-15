package code.ui.uistate.reader

import com.codelab.basiclayouts.R
import code.model.reader.readerFavoriteAuthor


data class ReaderFavouriteScreenUiState(
    val readerId : Int = R.integer.READERID,
    val storyId : Int = 1,
    val authors: List<readerFavoriteAuthor> = listOf(),
    )
