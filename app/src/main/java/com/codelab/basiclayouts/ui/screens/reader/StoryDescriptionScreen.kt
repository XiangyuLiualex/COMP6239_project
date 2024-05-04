package com.codelab.basiclayouts.ui.screens.reader

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codelab.basiclayouts.R

@Preview
@Composable
fun StoryScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        StoryHeader()
        Spacer(modifier = Modifier.height(8.dp))
        StoryDescription()
        Spacer(modifier = Modifier.height(8.dp))
        CommentsSection()
        Spacer(modifier = Modifier.height(8.dp))
        StartJourneyButton()
    }
}

@Composable
fun StoryHeader() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.ab1_inversions),
            contentDescription = "Story Cover",
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(text = "Story Title", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Author Name", fontSize = 16.sp)
                IconButton(onClick = { /* Handle favorite */ }) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
                }
            }
        }
    }
}

@Composable
fun StoryDescription() {
    Text(text = "Description", fontWeight = FontWeight.Bold, fontSize = 16.sp)
    BasicText(text = "Here goes the introduction of the story, which can span multiple lines depending on the content.")
}

@Composable
fun CommentsSection() {
    Text(text = "Comments", fontWeight = FontWeight.Bold, fontSize = 16.sp)
    // Mock comments list
    listOf("Nice story!", "Loved it!", "Can't wait for more!").forEachIndexed { index, comment ->
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Placeholder for user avatar
            Box(modifier = Modifier.size(40.dp).background(androidx.compose.ui.graphics.Color.Gray))
            Text(text = "User$index: $comment", modifier = Modifier.weight(1f))
            Text(text = "${(10..100).random()} Likes")
        }
    }
}

@Composable
fun StartJourneyButton() {
    Button(onClick = { /* Start reading */ }) {
        Text(text = "Start Your Journey")
    }
}

