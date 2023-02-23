package com.example.todolistcompose.components.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolistcompose.R
import com.example.todolistcompose.components.todo.showToast
import com.example.todolistcompose.data.db.entities.Category
import com.example.todolistcompose.viewModels.CategoryViewModel


@Composable
fun CategoryItem(
    navController: NavController,
    category: Category,
    categoryVM: CategoryViewModel = viewModel(),
) {
    val localContext = LocalContext.current.applicationContext
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp)
            .clickable {
                showToast(localContext, "Card View")
            }
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = 4.dp,
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 8.dp)
            ) {
                Text(
                    text = category.name,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth(),
                )

                Row(Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(id = R.string.lorem_ipsum),
                        fontSize = 14.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),

                        )
                    IconButton(onClick = {
                        categoryVM.setCategory(category)
                        categoryVM.isEditDialogShown = true
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Create,
                            contentDescription = "Edit Category"
                        )
                    }
                    IconButton(onClick = {
                        categoryVM.setCategory(category)
                        categoryVM.isDeleteDialogShown = true
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete Category"
                        )
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryItemPreview() {
    CategoryItem(rememberNavController(), Category(1, "Category Name"))
}