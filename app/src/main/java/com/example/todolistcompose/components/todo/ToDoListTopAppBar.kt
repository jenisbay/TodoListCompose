package com.example.todolistcompose.components.todo

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolistcompose.screens.Screen


@Composable
fun ToDoListTopAppBar(navController: NavController) {

    var expanded by remember { mutableStateOf(false) }
    val localContext = LocalContext.current.applicationContext
    TopAppBar(
        title = { Text("Top App Bar") },
        navigationIcon = {
            IconButton(onClick = { showToast(localContext, "NavIcon") }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Navigation Icon")
            }
        },
        actions = {

            IconButton(onClick = {
                 expanded = !expanded
            }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Delete")
            }

            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                DropdownMenuItem(onClick = { navController.navigate(Screen.CategoryList.route) }) {
                    Text("Category List")
                }
            }
        },
        elevation = 8.dp
    )
}

fun showToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}


@Preview(showSystemUi = true)
@Composable
fun TopAppBarComposePreview() {
    Box(modifier = Modifier.fillMaxWidth()) {
        ToDoListTopAppBar(rememberNavController())
    }
}