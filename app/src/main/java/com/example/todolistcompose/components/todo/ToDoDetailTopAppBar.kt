package com.example.todolistcompose.components.todo

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolistcompose.viewModels.ToDoViewModel


@Composable
fun ToDoDetailTopAppBar(navController: NavController, todoVM: ToDoViewModel = viewModel()) {
    val ctx = LocalContext.current.applicationContext
    TopAppBar(
        title = { },
        navigationIcon = {
            IconButton(onClick = {
                todoVM.setToDo(null)
                navController.navigateUp()
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back Arraw")
            }
        },
        actions = {
            IconButton(onClick = { showToast(ctx, "Share Task") }) {
                Icon(imageVector = Icons.Filled.Share, contentDescription = "Share Task")
            }
            IconButton(onClick = {
                todoVM.isDeleteDialogShown = true
            }) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete Task")
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun ToDoDetailTopAppBarPreview() {
    ToDoDetailTopAppBar(rememberNavController(), viewModel())
}