package com.example.todolistcompose.screens

sealed class Screen(val route: String) {

    // Category Screens
    object CategoryList: Screen(route = "category_list")

    // ToDo Screens
    object ToDoList: Screen(route = "todo_list")
    object ToDoAdd: Screen(route = "todo_add")
    object ToDoDetail: Screen(route = "todo_detail/{todo}"){
        fun parseRoute(arg: String): String {
            return "todo_detail/$arg"
        }
    }
}