package com.codelab.basiclayouts.ui.screens.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.darkColors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codelab.basiclayouts.R
import com.codelab.basiclayouts.model.Story
import com.codelab.basiclayouts.ui.theme.BorderColor
import com.codelab.basiclayouts.ui.theme.BrandColor
import com.codelab.basiclayouts.ui.theme.Tertirary

private enum class PageItem {
    MAIN_PAGE,
    FAVORITE_PAGE,
    LIBRARY_PAGE,
    PROFILE_PAGE,
}

@Composable
fun GuestScreen (nav: NavHostController) {
    val pageIndex = remember {
        mutableStateOf(PageItem.MAIN_PAGE)
    }

    Surface (
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold (
            bottomBar = { BottomBar(pageIndex) },
        ) { _ ->
            when (pageIndex.value) {
                PageItem.MAIN_PAGE     -> GuestMain(nav)
                PageItem.FAVORITE_PAGE -> GuestFavorate()
                PageItem.LIBRARY_PAGE  -> GuestLibrary()
                PageItem.PROFILE_PAGE  -> GuestPrivate()
            }
        }
    }
}

@Composable
fun StoryDescription (story: Story, progress: Boolean = false) {
    Row (
        modifier = Modifier.fillMaxWidth()
    ){
        Image (
            painter = painterResource(id = R.drawable.share_guest_navi_item_libry),
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Column (
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = story.author,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(1.dp))
            Text(
                text = story.title,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.padding(1.dp))
            Text(
                text = story.description,
                fontSize = 13.sp
            )
            if (progress) {
                Spacer(modifier = Modifier.padding(1.dp))
                Text(
                    text = "progress: ${story.progress}",
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
fun GuestMain (navController: NavHostController) {
    val storyList = remember {
        mutableStateOf<List<Story>>(queryStory())
    }
    val storyCategory = remember {
        if (!storyList.value.isEmpty())
            mutableStateOf(storyList.value[0].category)
        else
            mutableStateOf("")
    }

    Column () {
        TopBar(navController = navController, storyList)
        Spacer(modifier = Modifier.padding(5.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Category",
                fontSize = 18.sp,
                color = BrandColor
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Row (
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.horizontalScroll(rememberScrollState())
            ) {
                for (story in storyList.value) {
                    Button(
                        enabled = true,
                        onClick = {
                            storyCategory.value = story.category
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        modifier = Modifier.background(Color.Transparent)
                    ) {
                        Text(
                            text = story.category,
                            fontSize = 18.sp,
                            color = if (storyCategory.value == story.category)
                                Color.Blue
                            else
                                Color.Black,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            modifier = Modifier.width(30.dp),
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        LazyColumn () {
            val filterStories = storyList.value.filter {
                it.category == storyCategory.value
            }
            items(filterStories.size) {
                StoryDescription(filterStories[it])
            }
        }
    }
}

private fun queryStory(keyword: String = "", author: Boolean = true): List<Story> {
    //TODO
    return listOf()
}

@Composable
fun GuestFavorate () {
    val storyList = queryFavoriteStory()
    LazyColumn () {
        items(storyList.size) {
            StoryDescription(storyList[it])
        }
    }
}

private fun queryFavoriteStory (): List<Story> {
    //TODO
    return listOf()
}

@Composable
fun GuestLibrary () {
    val storyList = queryLibraryStory()
    LazyColumn () {
        items(storyList.size) {
            StoryDescription(storyList[it], true)
        }
    }
}

private fun queryLibraryStory (): List<Story> {
    //TODO
    return listOf()
}

@Composable
fun GuestPrivate () {
}

//@Composable
//fun GuestInformation (id: Int, name: String, desc: String) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .background(
//                color = Color.Magenta
//            )
//            .fillMaxWidth()
//    ) {
//        Icon (
//            painter = painterResource(id = id),
//            contentDescription = null,
//            modifier = Modifier.size(35.dp)
//        )
//        Spacer(modifier = Modifier.padding(5.dp))
//        Column {
//            Text(text = name)
//            Spacer(modifier = Modifier.padding(5.dp))
//            Text(text = desc)
//        }
//    }
//}

@Composable
fun TopBar (navController: NavHostController, storyList: MutableState<List<Story>>) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.padding(3.dp))
        Image (
            painterResource(id = R.drawable.share_baby_mummy),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(30.dp)
                .clip(shape = RoundedCornerShape(50))
        )
        Spacer (modifier = Modifier.padding(5.dp))
        TopSearchBar(storyList)
        Button(
            enabled = true,
            onClick = {
                navController.navigate("LoginScreen")
            },
            colors = ButtonDefaults.buttonColors (
                containerColor = Color.Transparent
            ),
            modifier = Modifier.background(Color.Transparent)
        ) {
            Text(
                text = "Sign in",
                fontSize = 18.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun TopSearchBar (storyList: MutableState<List<Story>>) {
    var queryAuthor by remember {
        mutableStateOf(true)
    }
    var queryWord by remember {
        mutableStateOf("")
    }

    Column (
        modifier = Modifier
            .height(100.dp)
            .width(260.dp)
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {
                queryWord = it
                storyList.value = queryStory(queryWord, queryAuthor)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Go,
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BrandColor,
                unfocusedBorderColor = BorderColor,
                focusedTextColor = Color.Black,
                focusedLeadingIconColor = BrandColor,
                unfocusedLeadingIconColor = Tertirary
            ),
            placeholder = {
                Text(text = "search...", color = Tertirary)
            },
            shape = MaterialTheme.shapes.small,
        )
        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            TopRadioButton(text = "author", selected = queryAuthor) {
                queryAuthor = true
            }
            Spacer(modifier = Modifier.padding(8.dp))
            TopRadioButton(text = "book", selected = !queryAuthor ) {
                queryAuthor = false
            }
        }
    }
}

@Composable
fun TopRadioButton(text: String, selected: Boolean, onSelect: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable(onClick = onSelect)
    ) {
        RadioButton(selected = selected, onClick = onSelect)
        Text(text = text, fontSize = 13.sp)
    }
}

@Composable
private fun BottomBar (pageIndex: MutableState<PageItem>) {
    NavigationBar () {
        NavigationBarItem(
            selected = pageIndex.value == PageItem.MAIN_PAGE,
            onClick = {
                pageIndex.value = PageItem.MAIN_PAGE
            },
            icon = { Image(
                painter = painterResource(id = R.drawable.share_guest_navi_item_main),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            ) },
            label = { Text(
                text = "main",
                fontSize = 15.sp
            ) }
        )
        NavigationBarItem(
            selected = pageIndex.value == PageItem.FAVORITE_PAGE,
            onClick = {
                pageIndex.value = PageItem.FAVORITE_PAGE
            },
            icon = { Image(
                painter = painterResource(id = R.drawable.share_guest_navi_item_favo),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            ) },
            label = { Text(
                text = "favorite",
                fontSize = 15.sp
            ) }
        )
        NavigationBarItem(
            selected = pageIndex.value == PageItem.LIBRARY_PAGE,
            onClick = {
                pageIndex.value = PageItem.LIBRARY_PAGE
            },
            icon = { Image(
                painter = painterResource(id = R.drawable.share_guest_navi_item_libry),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            ) },
            label = { Text(
                text = "library",
                fontSize = 15.sp
            )}
        )
        NavigationBarItem(
            selected = pageIndex.value == PageItem.PROFILE_PAGE,
            onClick = {
                pageIndex.value = PageItem.PROFILE_PAGE
            },
            icon = { Image(
                painter = painterResource(id = R.drawable.share_guest_navi_item_private),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            ) },
            label = { Text(
                text = "profile",
                fontSize = 15.sp
            ) }
        )
    }
}

@Preview
@Composable
fun GuestScreenPreview () {
    val navController = rememberNavController()
    GuestScreen(navController)
}