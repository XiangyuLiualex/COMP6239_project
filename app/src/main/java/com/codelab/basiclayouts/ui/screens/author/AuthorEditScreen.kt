//package com.codelab.basiclayouts.ui.screens.author
//
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.wrapContentSize
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material.icons.filled.Close
//import androidx.compose.material.icons.outlined.Dataset
//import androidx.compose.material.icons.outlined.Edit
//import androidx.compose.material.icons.outlined.Link
//import androidx.compose.material3.Button
//import androidx.compose.material3.Checkbox
//import androidx.compose.material3.DropdownMenu
//import androidx.compose.material3.DropdownMenuItem
//import androidx.compose.material3.ExtendedFloatingActionButton
//import androidx.compose.material3.HorizontalDivider
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.codelab.basiclayouts.ui.theme.AppTheme
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.window.Dialog
//import androidx.compose.material.icons.filled.AccountCircle
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material.icons.filled.Spa
//import androidx.compose.material3.NavigationRail
//import androidx.compose.material3.NavigationRailItem
//import androidx.compose.ui.res.stringResource
//import androidx.navigation.NavController
//import com.codelab.basiclayouts.R
//
//
//@Composable
//fun SootheNavigationRail(modifier: Modifier = Modifier) {
//    NavigationRail(
//        modifier = modifier.padding(start = 4.dp, end = 4.dp),
//        containerColor = MaterialTheme.colorScheme.background,
//    ) {
//        Column(
//            modifier = modifier.fillMaxHeight(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            NavigationRailItem(
//                icon = {
//                    Icon(
//                        imageVector = Icons.Default.Spa,
//                        contentDescription = null
//                    )
//                },
//                label = {
//                    Text(stringResource(R.string.bottom_navigation_home))
//                },
//                selected = true,
//                onClick = {}
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            NavigationRailItem(
//                icon = {
//                    Icon(
//                        imageVector = Icons.Default.AccountCircle,
//                        contentDescription = null
//                    )
//                },
//                label = {
//                    Text(stringResource(R.string.bottom_navigation_profile))
//                },
//                selected = false,
//                onClick = {}
//            )
//        }
//    }
//}
//
//@Composable
//fun OptionButton(optionName: String, onRemove: () -> Unit) {
//    Row {
//        Button(onClick = {}){
//            Text(optionName)
//            IconButton(onClick = onRemove) {
//                Icon(Icons.Filled.Close, contentDescription = "Remove")
//            }
//        }
//    }
//}
//
//
//@Composable
//fun FormComponent(onCancel: () -> Unit, onConfirm: (String) -> Unit) {
//    var optionName by rememberSaveable { mutableStateOf("") }
//    var chapterName by rememberSaveable { mutableStateOf("") }
////    var isTheEnd by rememberSaveable { mutableStateOf(false) }
//
//    Column(
//        modifier = Modifier
//            .padding(16.dp)
//            .fillMaxWidth(),
//        verticalArrangement = Arrangement.spacedBy(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//        TextField(
//            value = optionName,
//            onValueChange = { optionName = it },
//            label = { Text("Option Name") }
//        )
//        TextField(
//            value = chapterName,
//            onValueChange = { chapterName = it },
//            label = { Text("Connected Chapter Name") }
//        )
//        Row(
//            horizontalArrangement = Arrangement.SpaceEvenly,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Button(onClick = onCancel) {
//                Text("Cancel")
//            }
//            Button(onClick = { onConfirm(optionName) }) {
//                Text("Confirm")
//            }
//        }
//    }
//}
//
//
//@Composable
//fun RemovableTextField(text: String, onTextChange: (String) -> Unit, onRemove: () -> Unit) {
//    Row(verticalAlignment = Alignment.CenterVertically) {
//        TextField(
//            value = text,
//            onValueChange = onTextChange,
//            label = { Text("Enter text") }
//        )
//        IconButton(onClick = onRemove) {
//            Icon(Icons.Filled.Close, contentDescription = "Remove")
//        }
//    }
//}
//
//@Composable
//fun AddElementMenu(modifier: Modifier = Modifier){
//    var expanded by remember { mutableStateOf(false) }
//    var textFields by remember { mutableStateOf(listOf<String>()) }  // 使用列表存储每个输入框的内容
//    var showForm by remember { mutableStateOf(false) }
//    var buttons by remember { mutableStateOf(listOf<String>()) }
////    var elements by remember { mutableStateOf(listOf<Element>()) }  // 使用 Element 类型的列表存储元素
//
//
//    if (showForm) {
//        Dialog(onDismissRequest = { showForm = false }) {
//            Surface(
//                modifier = Modifier.padding(8.dp),
//                color = MaterialTheme.colorScheme.surfaceVariant, // 根据主题选择适合的背景颜色
//                shape = MaterialTheme.shapes.medium // 轻微的圆角
//            ){
//                FormComponent(
//                    onCancel = { showForm = false },
//                    onConfirm = { optionName ->
//                        buttons = listOf(optionName) + buttons
//                        showForm = false
//                    }
//                )
//            }
//        }
//    } else {
//
//        Column {
//            for ((index, textField) in textFields.withIndex()) {
//                RemovableTextField(
//                    text = textField,
//                    onTextChange = { newText ->
//                        textFields = textFields.toMutableList().also { it[index] = newText }
//                    },
//                    onRemove = {
//                        textFields = textFields.toMutableList().also { it.removeAt(index) }
//                    }
//                )
//            }
//        }
//
//        Column {
//            buttons.forEach { buttonName ->
//                OptionButton(buttonName, onRemove = { buttons = buttons.filterNot { it == buttonName } })
//            }
//        }
//
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .wrapContentSize(Alignment.Center)
//        ) {
//            ExtendedFloatingActionButton(onClick = { expanded = true }) {
//                Icon(Icons.Filled.Add, contentDescription = "Localized description")
//                Text(text = "Add Element")
//            }
//
//            DropdownMenu(
//                expanded = expanded,
//                onDismissRequest = { expanded = false }
//            ) {
//                DropdownMenuItem(
//                    text = { Text("Text") },
//                    onClick = {
//                        textFields = textFields + ""  // 添加新的空字符串表示新输入框
//                        expanded = false
//                    },
//                    leadingIcon = {
//                        Icon(Icons.Outlined.Edit, contentDescription = null)
//                    })
//                DropdownMenuItem(
//                    text = { Text("Media") },
//                    onClick = { /* Handle settings! */ },
//                    leadingIcon = {
//                        Icon(
//                            Icons.Outlined.Link,
//                            contentDescription = null
//                        )
//                    })
//                HorizontalDivider()
//                DropdownMenuItem(
//                    text = { Text("Options") },
//                    onClick = { showForm = true
//                        expanded = false },
//                    leadingIcon = {
//                        Icon(
//                            Icons.Outlined.Dataset,
//                            contentDescription = null
//                        )
//                    })
//            }
//        }
//
//    }
//}
//
////@Composable
////fun AddEditElement(){
////    ExtendedFloatingActionButton(
////        onClick = { /* do something */ },
////        icon = { Icon(Icons.Filled.Add, "Localized description") },
////        text = { Text(text = "Add Element") },
////    )
////
////}
//
//@Composable
//fun EditComponent(modifier: Modifier = Modifier) {
//    var chapterName by rememberSaveable { mutableStateOf("") }
//    var isTheEnd by rememberSaveable { mutableStateOf(false) }
//    Column(verticalArrangement = Arrangement.SpaceEvenly, // 垂直方向上均匀分布
//        horizontalAlignment = Alignment.CenterHorizontally // 水平方向上居中
//    ){
//        Spacer(Modifier.height(16.dp))
//
//        TextField(
//            value = chapterName,
//            onValueChange = { chapterName = it },
//            label = { Text("Chapter Name") },
//        )
//        Spacer(Modifier.height(16.dp))
//        AddElementMenu()
//        Spacer(Modifier.height(36.dp))
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Checkbox(
//                checked = isTheEnd,
//                onCheckedChange = { isTheEnd = it }
//            )
//            Text("Is the end")
//        }
////        Spacer(Modifier.height(16.dp))
//        Button(onClick={}){
//            Text("Save")
//        }
//    }
//
//}
//
//@Composable
//fun AuthorEditStoryScreen() {
//    var isNavigationVisible by rememberSaveable { mutableStateOf(false) } // 状态控制导航栏是否可见
//
//    AppTheme {
//        Scaffold(
//            bottomBar = { BottomNavigation() }
//        ) { padding ->
//            Column(
//                modifier = Modifier
//                    .verticalScroll(rememberScrollState())
//                    .fillMaxSize()
//                    .padding(padding),
//                verticalArrangement = Arrangement.SpaceEvenly,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Row {
//                    if (isNavigationVisible) { // 控制导航栏显示或隐藏
//                        SootheNavigationRail()
//                    }
//                    IconButton(
//                        onClick = { isNavigationVisible = !isNavigationVisible }
//                    ) {
//                        Icon(
//                            imageVector = if (isNavigationVisible) Icons.Filled.Close else Icons.Filled.Menu, // 根据状态显示不同图标
//                            contentDescription = "Toggle navigation"
//                        )
//                    }
//                    EditComponent(Modifier.fillMaxHeight())
//                }
//            }
//        }
//    }
//}
//
//
//
////@Preview
////@Composable
////fun AuthorEditStoryScreenPreview(){
////    AuthorEditStoryScreen()
////}
//
////@Preview(showBackground = true, name = "TextField Preview")
////@Composable
////fun PreviewMyTextField() {
////    MaterialTheme {
////        Surface(modifier = Modifier.padding(16.dp)) {
////            MyTextField()
////        }
////    }
////}
//
////@Preview
////@Composable
////fun AddEditElementPreview(){
////    AddEditElement()
////}
//
////@Preview
////@Composable
////fun AddElementMenuPreview(){
////    AddElementMenu()
////}
//
//
//
//
