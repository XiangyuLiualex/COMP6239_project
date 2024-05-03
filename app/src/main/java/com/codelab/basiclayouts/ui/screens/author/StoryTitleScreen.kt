package com.codelab.basiclayouts.ui.screens.author

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.basiclayouts.ui.theme.AppTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController


@Composable
fun InputComponent(navController:NavController, modifier: Modifier = Modifier) {
    var title by rememberSaveable { mutableStateOf("") }
    var category by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    Column(verticalArrangement = Arrangement.SpaceEvenly, // 垂直方向上均匀分布
        horizontalAlignment = Alignment.CenterHorizontally // 水平方向上居中
    ){
        Spacer(Modifier.height(16.dp))
        Text("Please input the information for your new Story!",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
        Spacer(Modifier.height(16.dp))
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
        )
        Spacer(Modifier.height(16.dp))
        TextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Category") },
        )
        Spacer(Modifier.height(16.dp))
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
        )
        Spacer(Modifier.height(16.dp))
        FilledTonalButton(onClick = { /*TODO*/ }) {
            Text("Upload Story Cover")
        }
        Spacer(Modifier.height(26.dp))
        Button(onClick={navController.navigate("authorEditScreen")}){
            Text("Start Writting")
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick={}){
            Text("Good to public")
        }

    }

}

//@Composable
//fun AuthorNewStoryScreen(navController:NavController) {
//    AppTheme {
//        Scaffold(
//            bottomBar = { BottomNavigation(navController) }
//        ) { padding ->
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(padding),  // 应用由 Scaffold 提供的 padding，保证内容不被 BottomNavigation 遮挡
//                verticalArrangement = Arrangement.SpaceEvenly,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                InputComponent(navController,Modifier.fillMaxHeight())  // 使 MyTextField 填充整个宽度
//            }
//        }
//    }
//}


//@Preview(showBackground = true, name = "TextField Preview")
//@Composable
//fun PreviewMyTextField() {
//    MaterialTheme {
//        Surface(modifier = Modifier.padding(16.dp)) {
//            MyTextField()
//        }
//    }
//}
//
//@Preview(widthDp = 360, heightDp = 640)
//@Composable
//fun AuthorNewStoryScreenPreview() {
//    AuthorNewStoryScreen()
//}