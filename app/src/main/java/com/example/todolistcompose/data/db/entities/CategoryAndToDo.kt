package com.example.todolistcompose.data.db.entities

import androidx.room.Embedded
import androidx.room.Relation


data class CategoryAndToDo(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "pk",
        entityColumn = "categoryId"
    )
    val todos: List<ToDo>
)