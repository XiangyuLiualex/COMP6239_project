package com.codelab.basiclayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codelab.basiclayouts.app.StoryApp
import com.codelab.basiclayouts.ui.screen.AuthorEditMainScreen
import com.codelab.basiclayouts.ui.screen.StoryEditScreen
import com.codelab.basiclayouts.ui.screens.author.ParentScreen
import com.codelab.basiclayouts.ui.screens.reader.ReaderFavouriteScreen
import com.codelab.basiclayouts.ui.theme.DarkTheme
import com.codelab.basiclayouts.data.RetrofitInstance
import com.codelab.basiclayouts.ui.screens.reader.ReaderLibraryScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化 RetrofitInstance
        RetrofitInstance.initialize(this)
        setContent {
            DarkTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                      StoryApp()
//                    ReaderFavouriteScreen()
//                    AuthorMainScreen()
//                    StoryEditScreen()
//                    ParentScreen()
                      //ReaderLibraryScreen()

                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DarkTheme {
        StoryApp()
    }
}