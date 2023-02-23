package com.example.todolistcompose.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolistcompose.data.db.entities.Category
import com.example.todolistcompose.data.db.entities.ToDo


@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Update
    suspend fun updateCategory(category: Category)

    @Query("SELECT * FROM categories")
    fun getAllCategories(): LiveData<List<Category>>


    @Query("SELECT * FROM todos ORDER BY pk DESC")
    fun getAllToDos(): LiveData<List<ToDo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertToDo(todo: ToDo)

    @Delete
    suspend fun deleteToDo(todo: ToDo)

    @Update
    suspend fun updateToDo(todo: ToDo)

}