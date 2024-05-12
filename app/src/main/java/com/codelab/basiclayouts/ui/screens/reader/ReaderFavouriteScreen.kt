package com.codelab.basiclayouts.ui.screens.reader

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.codelab.basiclayouts.model.reader.readerFavoriteAuthor
import com.codelab.basiclayouts.ui.viewmodel.reader.ReaderFavouriteScreenViewModel


//主页面
@Composable
fun ReaderFavouriteScreen(viewModel: ReaderFavouriteScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    var searchQuery by remember { mutableStateOf("") }
    // 观察 ViewModel 中的 StateFlow 并获取当前状态
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Author List") },
                actions = {
                    SearchAction(searchQuery) { query ->
                        searchQuery = query
                        viewModel.searchAuthors(query) // Assume this function is implemented in ViewModel
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(items = uiState.authors) { author ->
                AuthorCard(author, viewModel)
            }
        }
    }
}


//搜索
@Composable
fun SearchAction(query: String, onQueryChanged: (String) -> Unit) {
    var isSearching by remember { mutableStateOf(false) }
    if (isSearching) {
        TextField(
            value = query,
            onValueChange = onQueryChanged,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search authors") },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                isSearching = false // Hide keyboard on search
                defaultKeyboardAction(ImeAction.Search)
            }),
            trailingIcon = {
                IconButton(onClick = { isSearching = false }) {
                    Icon(Icons.Default.Close, contentDescription = "Close search")
                }
            }
        )
    } else {
        IconButton(onClick = { isSearching = true }) {
            Icon(Icons.Default.Search, contentDescription = "Search")
        }
    }
}


//喜欢的作者卡片
@Composable
fun AuthorCard(author: readerFavoriteAuthor, viewModel: ReaderFavouriteScreenViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(author.photoUrl),
                contentDescription = "Author Avatar",
                modifier = Modifier.size(60.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(author.username, fontWeight = FontWeight.Bold)
                Text(author.selfDescription, style = MaterialTheme.typography.body2)
            }
            IconButton(
                onClick = { viewModel.tFavoriteAuthorDel(1,author.userId) }//替换为全局读者ID
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Unfavorite",
                    tint = Color.Red
                )
            }
        }
    }
}



