package com.example.todolistcompose.components.todo

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.todolistcompose.viewModels.ToDoViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*


@Composable
fun DueTimeField(todoVM: ToDoViewModel) {

    var dueTime by remember {
        mutableStateOf(
            if (todoVM.calendar != null) {
                LocalTime.of(
                    todoVM.calendar!![Calendar.HOUR],
                    todoVM.calendar!![Calendar.MINUTE]
                )
            } else {
                LocalTime.NOON
            }
        )
    }
    todoVM.setTime(dueTime)
    val timeDialogState = rememberMaterialDialogState()
    val formattedTime by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("HH:mm a", Locale.ENGLISH)
                .format(dueTime)
        }
    }

    MaterialDialog(dialogState = timeDialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }) {
        timepicker(
            initialTime = dueTime,
            title = "Pick a time",
            is24HourClock = true,
        ) { time ->
            dueTime = time
            todoVM.setTime(time)
        }
    }

    Row(Modifier.fillMaxWidth()) {
        TextField(
            value = formattedTime,
            onValueChange = { },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            readOnly = true,
        )
        IconButton(onClick = { timeDialogState.show() }) {
            Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "Pick time")
        }
    }
}