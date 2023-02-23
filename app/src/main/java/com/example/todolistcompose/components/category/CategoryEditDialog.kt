package com.example.todolistcompose.components.category

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolistcompose.viewModels.CategoryViewModel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CategoryEditDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    categoryVM: CategoryViewModel = viewModel()
) {

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth(0.95f),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(text = "Edit Category")

                var name by remember { mutableStateOf(categoryVM.category?.name)}
                TextField(
                    value = name.toString(),
                    onValueChange = {
                        name = it
                        categoryVM.category?.name = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { onDismiss() }) {
                        Text(text = "Cancel", fontSize = 18.sp)
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    TextButton(onClick = { onConfirm() }) {
                        Text(text = "Save", fontSize = 18.sp)
                    }
                }

            }


        }

    }

}