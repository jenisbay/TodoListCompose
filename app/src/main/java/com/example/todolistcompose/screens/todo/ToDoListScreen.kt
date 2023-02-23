package com.example.todolistcompose.screens.todo

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todolistcompose.components.todo.ToDoListTopAppBar
import com.example.todolistcompose.components.todo.ToDoItem
import com.example.todolistcompose.screens.Screen
import com.example.todolistcompose.viewModels.ToDoViewModel


@Composable
fun ToDoListScreen(
    navController: NavController,
    toDoViewModel: ToDoViewModel = viewModel(),
){

    val ctx = LocalContext.current.applicationContext
    toDoViewModel.getAllToDos(ctx)
    val todos = toDoViewModel.todos.observeAsState()

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ToDoListTopAppBar(navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(route = Screen.ToDoAdd.route)
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "FAB")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->

        LazyColumn(modifier = Modifier.padding(paddingValues)){
            todos.value?.let { _todos ->
                items(count = _todos.size, itemContent = { index ->
                    ToDoItem(navController, _todos[index])
                })


            }
        }

    }

}
