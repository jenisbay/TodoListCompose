package com.example.todolistcompose.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todolistcompose.R
import com.example.todolistcompose.data.db.entities.Category
import com.example.todolistcompose.data.db.entities.ToDo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader


@Database(entities = [ToDo::class, Category::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun dao(): ToDoDao

    companion object {

        @Volatile
        private var INSTANCE: ToDoDatabase? = null

        private val LOCK = Any()

        fun invoke(context: Context) = synchronized(LOCK) {
            INSTANCE ?: createDatabase(context).also { INSTANCE = it }
        }

        private fun createDatabase(context: Context): ToDoDatabase {
            return Room.databaseBuilder(
                context,
                ToDoDatabase::class.java,
                "todo.db"
            )
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {
                            fillWithInitialCategories(context)
                        }
                    }

                    private fun loadJSONArray(context: Context): JSONArray {
                        val inputStream = context.resources.openRawResource(R.raw.category_list)
                        BufferedReader(inputStream.reader()).use {
                            return JSONArray(it.readText())
                        }
                    }

                    private suspend fun fillWithInitialCategories(context: Context) {
                        val dao = invoke(context).dao()
                        try {
                            val categories = loadJSONArray(context)
                            for (i in 0 until categories.length()) {
                                val item = categories.getJSONObject(i)
                                dao.insertCategory(Category(item.getString("title")))
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                })
                .build()
        }
    }

}