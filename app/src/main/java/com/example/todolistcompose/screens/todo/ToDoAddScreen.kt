package com.example.todolistcompose.screens.todo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolistcompose.components.todo.ToDoAddForm
import com.example.todolistcompose.components.todo.ToDoAddTopAppBar
import com.example.todolistcompose.data.db.entities.ToDo
import com.example.todolistcompose.viewModels.ToDoViewModel


@Composable
fun ToDoAddScreen(
    navController: NavController,
    todoVM: ToDoViewModel = viewModel()
) {
    val ctx = LocalContext.current.applicationContext
    val scaffoldState = rememberScaffoldState()
    todoVM.setToDo(ToDo("", 0L, 0L))
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ToDoAddTopAppBar(navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                todoVM.insertToDo(ctx)
                navController.navigateUp()
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "FAB")
            }
        }
    ) { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ToDoAddForm(todoVM)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ToDoAddScreenPreview() {
    ToDoAddScreen(rememberNavController())
}