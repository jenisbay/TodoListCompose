package com.example.todolistcompose.utils

import java.text.SimpleDateFormat
import java.util.*


fun convertMillisecondsIntoDateTimeString(milliseconds: Long): String {
    return SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(milliseconds).toString()
}

fun convertMillisecondsIntoCalendar(milliseconds: Long): Calendar {
    val calendar = Calendar.getInstance(Locale.getDefault())
    calendar.timeInMillis = milliseconds
    return calendar
}