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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.codelab.basiclayouts.R
import com.codelab.basiclayouts.model.reader.readerFavoriteAuthor
import com.codelab.basiclayouts.ui.screens.shared.GuestFavorate
import com.codelab.basiclayouts.ui.screens.shared.GuestLibrary
import com.codelab.basiclayouts.ui.screens.shared.GuestMain
import com.codelab.basiclayouts.ui.screens.shared.GuestProfile
import com.codelab.basiclayouts.ui.theme.Primary
import com.codelab.basiclayouts.ui.viewmodel.reader.ReaderFavouriteScreenViewModel

@Composable
fun ReaderFavouriteScreen(
    navController: NavHostController,
    viewModel: ReaderFavouriteScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    var searchQuery by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Author List") },
                actions = {
                    SearchAction(searchQuery) { query ->
                        searchQuery = query
                        viewModel.searchAuthors(query)
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(items = uiState.authors) { author ->
                    AuthorCard(author, viewModel)
                }
            }
            Button(
                onClick = { viewModel.tFavoriteAuthorList(viewModel.uiState.value.readerId) },
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp)
            ) {
                Text("Refresh")
            }
        }
    }
}

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
                isSearching = false
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
                painter = rememberImagePainter(R.drawable.ab1_inversions),
                contentDescription = "Author Avatar",
                modifier = Modifier.size(60.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(author.username, fontWeight = FontWeight.Bold)
                Text(author.selfDescription, style = MaterialTheme.typography.body2)
            }
            IconButton(onClick = { viewModel.tFavoriteAuthorDel(R.integer.READERID, author.userId) }) {
                Icon(Icons.Filled.Favorite, contentDescription = "Unfavorite", tint = Color.Red)
            }
        }
    }
}

@Composable
private fun BottomBarForFav(pageIndex: MutableState<PageItemForFav>) {
    NavigationBar {
        NavigationBarItem(
            selected = pageIndex.value == PageItemForFav.MAIN_PAGE,
            onClick = { pageIndex.value = PageItemForFav.MAIN_PAGE },
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.share_franky),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            },
            label = {
                androidx.compose.material3.Text(
                    text = "main",
                    fontSize = 15.sp,
                    color = Primary,
                    fontWeight = FontWeight.Bold
                )
            }
        )
        NavigationBarItem(
            selected = pageIndex.value == PageItemForFav.FAVORITE_PAGE,
            onClick = { pageIndex.value = PageItemForFav.FAVORITE_PAGE },
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.share_owl),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            },
            label = {
                androidx.compose.material3.Text(
                    text = "favorite",
                    fontSize = 15.sp,
                    color = Primary,
                    fontWeight = FontWeight.Bold
                )
            }
        )
        NavigationBarItem(
            selected = pageIndex.value == PageItemForFav.LIBRARY_PAGE,
            onClick = { pageIndex.value = PageItemForFav.LIBRARY_PAGE },
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.share_bone),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            },
            label = {
                androidx.compose.material3.Text(
                    text = "library",
                    fontSize = 15.sp,
                    color = Primary,
                    fontWeight = FontWeight.Bold
                )
            }
        )
        NavigationBarItem(
            selected = pageIndex.value == PageItemForFav.PROFILE_PAGE,
            onClick = { pageIndex.value = PageItemForFav.PROFILE_PAGE },
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.share_lantern),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            },
            label = {
                androidx.compose.material3.Text(
                    text = "profile",
                    fontSize = 15.sp,
                    color = Primary,
                    fontWeight = FontWeight.Bold
                )
            }
        )
    }
}

private enum class PageItemForFav {
    MAIN_PAGE,
    FAVORITE_PAGE,
    LIBRARY_PAGE,
    PROFILE_PAGE,
}

@Composable
fun FavouriteScreen(nav: NavHostController) {
    val pageIndex = remember { mutableStateOf(PageItemForFav.MAIN_PAGE) }

    androidx.compose.material3.Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        androidx.compose.material3.Scaffold(bottomBar = { BottomBarForFav(pageIndex) }) { _ ->
            when (pageIndex.value) {
                PageItemForFav.MAIN_PAGE -> StoryHomeScreen(nav)
                PageItemForFav.FAVORITE_PAGE -> ReaderFavouriteScreen(nav)
                PageItemForFav.LIBRARY_PAGE -> ReaderLibraryScreen(nav)
                PageItemForFav.PROFILE_PAGE -> GuestProfile(nav)
            }
        }
    }
}
