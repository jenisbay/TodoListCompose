package com.example.todolistcompose.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val pk: Long,
    var name: String,
) {
    constructor(name: String) : this(0, name)
}