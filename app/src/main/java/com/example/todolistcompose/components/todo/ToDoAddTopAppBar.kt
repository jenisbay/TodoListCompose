package com.example.todolistcompose.components.todo

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolistcompose.viewModels.ToDoViewModel


@Composable
fun ToDoAddTopAppBar(
    navController: NavController,
    todoVM: ToDoViewModel = viewModel()
) {
    TopAppBar(
        title = { Text(text = "New Task") },
        navigationIcon = {
            IconButton(onClick = {
                todoVM.setToDo(null)
                navController.navigateUp()
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back Arraw")
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun ToDoAddTopAppBarPreview() {
    ToDoAddTopAppBar(rememberNavController())
}