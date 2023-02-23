package com.example.todolistcompose.data.db.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "todos")
@Parcelize
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val pk: Long,
    var title: String,
    var dueDateTime: Long,
    var categoryId: Long,
    var isFinished: Boolean,
) : Parcelable {
    constructor(
        title: String,
        dueDateTime: Long,
        categoryId: Long,
        isFinished: Boolean = false
    ): this(0, title, dueDateTime, categoryId, isFinished)

}