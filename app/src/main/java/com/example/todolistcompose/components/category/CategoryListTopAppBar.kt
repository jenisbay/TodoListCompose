package com.example.todolistcompose.components.category

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todolistcompose.R
import com.example.todolistcompose.viewModels.CategoryViewModel


@Composable
fun CategoryListTopAppBar(
    navController: NavController,
    categoryViewModel: CategoryViewModel = viewModel()
) {
    val ctx = LocalContext.current.applicationContext
    TopAppBar(
        title = { Text("Category List") },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Filled.ArrowBack, "")
            }
        },
        actions = {
            IconButton(onClick = {
                categoryViewModel.isAddDialogShown  =true
            }) {
                Icon(painter = painterResource(id = R.drawable.ic_category_add), "")
            }
        }
    )
}