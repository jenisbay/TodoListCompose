package com.example.todolistcompose.navigation

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todolistcompose.data.db.entities.ToDo
import com.example.todolistcompose.screens.category.CategoryListScreen
import com.example.todolistcompose.screens.Screen
import com.example.todolistcompose.screens.todo.ToDoAddScreen
import com.example.todolistcompose.screens.todo.ToDoListScreen
import com.example.todolistcompose.screens.todo.TodoDetailScreen
import com.example.todolistcompose.utils.AssetParamType
import com.example.todolistcompose.viewModels.ToDoViewModel


@Composable
fun BaseNavGraph(
    navController: NavHostController,
    toDoViewModel: ToDoViewModel = viewModel()
) {

    NavHost(
        navController = navController,
        startDestination = Screen.ToDoList.route
    ) {
        // ToDo Composable List
        composable(route = Screen.ToDoList.route) {
            ToDoListScreen(navController = navController)
        }
        composable(route = Screen.ToDoAdd.route) {
            ToDoAddScreen(navController = navController)
        }
        composable(route = Screen.ToDoDetail.route,
            arguments = listOf(
                navArgument("todo") {
                    type = AssetParamType()
                }
            )
        ) {
            val todo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.arguments?.getParcelable("todo", ToDo::class.java)
            } else {
                it.arguments?.getParcelable("todo")
            }
            TodoDetailScreen(navController = navController, todo = todo)
        }

        // Category Composable List
        composable(route = Screen.CategoryList.route) {
            CategoryListScreen(navController = navController)
        }
    }
}