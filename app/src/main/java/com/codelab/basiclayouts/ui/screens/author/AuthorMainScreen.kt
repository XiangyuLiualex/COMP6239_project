package com.codelab.basiclayouts.ui.screens.author

import com.codelab.basiclayouts.R

/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codelab.basiclayouts.ui.theme.AppTheme

@Composable
fun NewStoryElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    onClick:()->Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick=onClick,
        modifier=modifier,
        shape= CircleShape
    ){
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
            )
            Text(
                text = stringResource(text),
                modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }

}
//
// Step: Favorite collection card - Material Surface
@Composable
fun DraftStoryElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
        }
    }
}

// Step: Align your body row - Arrangements
@Composable
fun DraftRow(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 50.dp),
        modifier = modifier
    ) {
        items(draftCollectionsData) { item ->
            DraftStoryElement(item.drawable, item.text)
        }
    }
}

@Composable
fun DoneStoryElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
        }
    }
}

@Composable
fun DoneRow(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 50.dp),
        modifier = modifier
    ) {
        items(doneCollectionsData) { item ->
            DoneStoryElement(item.drawable, item.text)
        }
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
        )
        Box(modifier = Modifier
            .fillMaxWidth() // 确保使用全部可用宽度
            .padding(horizontal = 16.dp), // 与标题相同的水平padding以对齐
            contentAlignment = Alignment.Center) { // 在Box内居中内容
            content()
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier,navController:NavController) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(16.dp))

        HomeSection(title = R.string.new_story) {
            NewStoryElement(drawable = R.drawable.write_story, text = R.string.write_story,
                onClick = {navController.navigate("authorNewStoryScreen")})
        }
        HomeSection(title = R.string.draft_story) {
            DraftRow(modifier = Modifier.height(150.dp))
        }
        HomeSection(title = R.string.done_story) {
            DoneRow(modifier = Modifier.height(150.dp))
        }

        Spacer(Modifier.height(16.dp))
    }
}


// Step: Bottom navigation - Material
@Composable
public fun BottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Spa,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_home))
            },
            selected = true,
            onClick = {
                // 使用NavController导航到HomeScreen
//                navController.navigate("home")
//                {
//                    // 清理导航栈，确保返回时不会回到前一个页面
//                    popUpTo("home") {
//                        saveState = true
//                    }
//                    launchSingleTop = true
//                    restoreState = true
//                }
            }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_profile))
            },
            selected = false,
            onClick = {}
        )
    }
}


@Composable
fun AuthorMainScreen(navController: NavController) {
    AppTheme {
        Scaffold(
            bottomBar = { BottomNavigation() }
        ) { padding ->
            HomeScreen(Modifier.padding(padding),navController)
        }
    }
}


private val draftCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.draft1_forest_tour,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

private val doneCollectionsData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }


private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

//@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
//@Composable
//fun SearchBarPreview() {
//    MySootheTheme { SearchBar(Modifier.padding(8.dp)) }
//}

//@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
//@Composable
//fun NewStoryElementPreview() {
//    MySootheTheme {
//        NewStoryElement(
//            text = R.string.write_story,
//            drawable = R.drawable.write_story,
//            modifier = Modifier.padding(8.dp)
//
//        )
//    }
//}

//@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
//@Composable
//fun DraftStoryElementPreview() {
//    MySootheTheme {
//        DraftStoryElement(
//            text = R.string.draft1_forest_tour,
//            drawable = R.drawable.fc2_nature_meditations,
//            modifier = Modifier.padding(8.dp),
//        )
//    }
//}

//@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
//@Composable
//fun DraftRowPreview() {
//    MySootheTheme { DraftRow(modifier=Modifier.height(300.dp)) }
//}

//@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
//@Composable
//fun DoneRowPreview() {
//    MySootheTheme { DoneRow(modifier=Modifier.height(300.dp)) }
//}

//@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
//@Composable
//fun HomeSectionPreview() {
//    MySootheTheme {
//        HomeSection(R.string.draft_story) {
//            DraftRow(modifier=Modifier.height(300.dp))
//        }
//    }
//}

//@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 800)
//@Composable
//fun ScreenContentPreview() {
//    MySootheTheme { HomeScreen() }
//}

//@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
//@Composable
//fun BottomNavigationPreview() {
//    MySootheTheme { SootheBottomNavigation(Modifier.padding(top = 24.dp)) }
//}
//

//@Preview(widthDp = 360, heightDp = 640)
//@Composable
//fun AuthorMainScreenPreview() {
//    AuthorMainScreen(navController : NavController)
//}

