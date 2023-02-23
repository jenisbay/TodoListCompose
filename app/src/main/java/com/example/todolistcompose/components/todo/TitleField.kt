package com.example.todolistcompose.components.todo

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.todolistcompose.viewModels.ToDoViewModel


@Composable
fun TitleField(todoVM: ToDoViewModel) {
    var title by remember { mutableStateOf(todoVM.todo?.title) }
    TextField(
        value = title.toString(),
        onValueChange = {
            title = it
            todoVM.todo?.title = title.toString()
        }, modifier = Modifier.fillMaxWidth()
    )
}