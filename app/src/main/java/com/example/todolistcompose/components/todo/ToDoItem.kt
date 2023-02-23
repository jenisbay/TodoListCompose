package com.example.todolistcompose.components.todo

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todolistcompose.data.db.entities.ToDo
import com.example.todolistcompose.screens.Screen
import com.example.todolistcompose.utils.convertMillisecondsIntoDateTimeString
import com.google.gson.Gson


@Composable
fun ToDoItem(navController: NavController, todo: ToDo) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp)
            .clickable {
                val json: String = Uri.encode(Gson().toJson(todo))
                navController.navigate(Screen.ToDoDetail.parseRoute(json))
            }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = 1.dp,
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = todo.title,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 18.sp,
                )
                Text(
                    text = convertMillisecondsIntoDateTimeString(todo.dueDateTime),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 8.dp, 0.dp, 0.dp),
                    fontSize = 14.sp,
                    color = Color.Red,
                )
            }
        }
    }
}