package com.example.todolistcompose.utils

import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.example.todolistcompose.data.db.entities.ToDo
import com.google.gson.Gson

class AssetParamType : NavType<ToDo>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): ToDo? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            bundle.getParcelable(key, ToDo::class.java)
        } else {
            bundle.getParcelable(key)
        }

    }

    override fun parseValue(value: String): ToDo {
        return Gson().fromJson(value, ToDo::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: ToDo) {
        bundle.putParcelable(key, value)
    }
}