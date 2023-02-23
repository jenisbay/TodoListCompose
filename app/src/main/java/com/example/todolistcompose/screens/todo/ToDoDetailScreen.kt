package com.example.todolistcompose.screens.todo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todolistcompose.components.todo.showToast
import com.example.todolistcompose.components.todo.ToDoAddForm
import com.example.todolistcompose.components.todo.ToDoDeleteDialog
import com.example.todolistcompose.components.todo.ToDoDetailTopAppBar
import com.example.todolistcompose.data.db.entities.ToDo
import com.example.todolistcompose.utils.convertMillisecondsIntoCalendar
import com.example.todolistcompose.viewModels.ToDoViewModel


@Composable
fun TodoDetailScreen(
    navController: NavController,
    todo: ToDo?,
    todoVM: ToDoViewModel = viewModel(),
) {
    todo?.let { _todo ->
        todoVM.setToDo(_todo)
        todoVM.calendar = convertMillisecondsIntoCalendar(_todo.dueDateTime)
    }
    val ctx = LocalContext.current.applicationContext
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ToDoDetailTopAppBar(navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                todoVM.updateToDo(ctx)
                navController.navigateUp()
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "FAB")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            ToDoAddForm(todoVM)

            if (todoVM.isDeleteDialogShown){
                ToDoDeleteDialog(
                    onDismiss = {
                        todoVM.isDeleteDialogShown = false
                    },
                    onConfirm = {
                        todoVM.deleteToDo(ctx)
                        todoVM.isDeleteDialogShown = false
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}