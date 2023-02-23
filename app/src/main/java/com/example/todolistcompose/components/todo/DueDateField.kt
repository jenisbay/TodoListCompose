package com.example.todolistcompose.components.todo

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.todolistcompose.viewModels.ToDoViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


@Composable
fun DueDateField(todoVM: ToDoViewModel) {

    var dueDate by remember {
        mutableStateOf(
            if (todoVM.calendar != null) {
                LocalDate.of(
                    todoVM.calendar!![Calendar.YEAR],
                    todoVM.calendar!![Calendar.MONTH]+1,
                    todoVM.calendar!![Calendar.DAY_OF_MONTH]
                )
            } else {
                LocalDate.now()
            }

        )
    }
    todoVM.setDate(dueDate)
    val dateDialogState = rememberMaterialDialogState()
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("dd/MM/yyyy", Locale.ENGLISH)
                .format(dueDate)
        }
    }

    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        datepicker(
            initialDate = dueDate,
            title = "Pick a date",
        ) { date ->
            dueDate = date
            todoVM.setDate(date)
        }
    }

    Row(Modifier.fillMaxWidth()) {
        TextField(
            value = formattedDate,
            onValueChange = { },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        )
        IconButton(onClick = {
            dateDialogState.show()
        }) {
            Icon(imageVector = Icons.Filled.Edit, contentDescription = "Pick date")
        }
    }
}