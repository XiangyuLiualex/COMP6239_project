package com.codelab.basiclayouts.ui.screens.reader

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.codelab.basiclayouts.model.reader.FavoriteAuthor
import com.codelab.basiclayouts.ui.viewmodel.reader.ReaderFavouriteScreenViewModel

@Composable
fun ReaderFavouriteScreen(viewModel: ReaderFavouriteScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    // 观察 ViewModel 中的 StateFlow 并获取当前状态
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Author List") })
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(items = uiState.authors) { author ->
                AuthorCard(author,viewModel)
            }
        }
    }
}


@Composable
fun AuthorCard(author: FavoriteAuthor,viewModel: ReaderFavouriteScreenViewModel) {
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
                onClick = { viewModel.tFavoriteAuthorDel(1,author.authorId) }
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



