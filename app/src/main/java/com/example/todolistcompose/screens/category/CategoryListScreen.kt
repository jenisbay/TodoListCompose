package com.example.todolistcompose.screens.category

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolistcompose.components.category.*
import com.example.todolistcompose.viewModels.CategoryViewModel


@Composable
fun CategoryListScreen(
    navController: NavController,
    categoryVM: CategoryViewModel = viewModel()
) {

    val scaffoldState = rememberScaffoldState()
    val ctx: Context = LocalContext.current.applicationContext
    val categories = categoryVM.categories.observeAsState()
    categoryVM.getAllCategories(ctx)

    Scaffold(
        modifier = Modifier,
        scaffoldState = scaffoldState,
        topBar = {
            CategoryListTopAppBar(navController = navController)
        },
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            categories.value?.let { _categories ->
                items(count = _categories.size, itemContent = { index ->
                    CategoryItem(navController, _categories[index])
                })
            }
        }

        if (categoryVM.isAddDialogShown) {
            CategoryAddDialog(
                onDismiss = {
                    categoryVM.setCategory(null)
                    categoryVM.isAddDialogShown = false
                },
                onConfirm = {
                    categoryVM.insertCategory(ctx)
                    categoryVM.isAddDialogShown = false
                }
            )
        }

        if (categoryVM.isEditDialogShown) {
            CategoryEditDialog(
                onDismiss = {
                    categoryVM.setCategory(null)
                    categoryVM.isEditDialogShown = false
                },
                onConfirm = {
                    categoryVM.updateCategory(ctx)
                    categoryVM.isEditDialogShown = false
                }
            )
        }

        if (categoryVM.isDeleteDialogShown) {
            CategoryDeleteDialog(
                onDismiss = {
                    categoryVM.setCategory(null)
                    categoryVM.isDeleteDialogShown = false
                },
                onConfirm = {
                    categoryVM.deleteCategory(ctx)
                    categoryVM.isDeleteDialogShown = false
                }
            )
        }

    }
}

@Preview
@Composable
fun CategoryListScreenPreview() {
    CategoryListScreen(rememberNavController())
}