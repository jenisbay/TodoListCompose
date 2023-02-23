package com.example.todolistcompose.viewModels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistcompose.data.db.ToDoDatabase
import com.example.todolistcompose.data.db.entities.Category
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {
    var isAddDialogShown by mutableStateOf(false)
    var isEditDialogShown by mutableStateOf(false)
    var isDeleteDialogShown by mutableStateOf(false)

    private var _categories: LiveData<List<Category>> = MutableLiveData()
    val categories get() = _categories

    var category: Category? = null
        private set

    fun setCategory(category: Category?){
        this.category = category
    }

    fun getAllCategories(context: Context){
        ToDoDatabase.invoke(context).dao().also { _dao ->
            _categories = _dao.getAllCategories()
        }
    }

    fun insertCategory(ctx: Context){
        viewModelScope.launch {
            ToDoDatabase.invoke(ctx).dao().also { _dao ->
                category?.let { _dao.insertCategory(it) }
            }
            setCategory(null)
        }
    }

    fun deleteCategory(ctx: Context){
        viewModelScope.launch {
            ToDoDatabase.invoke(ctx).dao().also { _dao ->
                category?.let { _dao.deleteCategory(it) }
            }
            setCategory(null)
        }
    }

    fun updateCategory(ctx: Context){
        viewModelScope.launch {
            ToDoDatabase.invoke(ctx).dao().also { _dao ->
                category?.let { _dao.updateCategory(it) }
            }
            setCategory(null)
        }
    }
}
