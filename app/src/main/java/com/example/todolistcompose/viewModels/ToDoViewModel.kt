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
import com.example.todolistcompose.data.db.entities.ToDo
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.util.*


class ToDoViewModel : ViewModel() {

    var isDeleteDialogShown by mutableStateOf(false)

    private var _todos: LiveData<List<ToDo>> = MutableLiveData()
    val todos get() = _todos

    var todo: ToDo? = null
        private set

    var calendar: Calendar? by mutableStateOf(null)

    fun setDate(date: LocalDate){
        calendar?.let { _calendar ->
            _calendar[Calendar.YEAR] = date.year
            _calendar[Calendar.MONTH] = date.month.value
            _calendar[Calendar.DAY_OF_MONTH] = date.dayOfMonth
        }
    }

    fun setTime(time: LocalTime){
        calendar?.let {_calendar ->
            _calendar[Calendar.HOUR] = time.hour
            _calendar[Calendar.MINUTE] = time.minute
        }
    }

    fun setToDo(todo: ToDo?) {
        this.todo = todo
    }

    fun getAllToDos(context: Context) {
        ToDoDatabase.invoke(context).dao().also { _dao ->
            _todos = _dao.getAllToDos()
        }
    }

    fun insertToDo(context: Context) {
        viewModelScope.launch {
            ToDoDatabase.invoke(context).dao().also { _dao ->
                todo?.let { _todo ->
                    _todo.dueDateTime = calendar!!.timeInMillis
                    _dao.insertToDo(_todo)
                }
            }
            setToDo(null)
        }
    }

    fun deleteToDo(context: Context) {
        viewModelScope.launch {
            ToDoDatabase.invoke(context).dao().also { _dao ->
                todo?.let { _dao.deleteToDo(it) }
            }
            setToDo(null)
        }
    }

    fun updateToDo(ctx: Context) {
        viewModelScope.launch {
            ToDoDatabase.invoke(ctx).dao().also { _dao ->
                todo?.let {
                    _dao.updateToDo(it)
                }
            }
            setToDo(null)
        }
    }


}