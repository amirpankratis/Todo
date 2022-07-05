package com.example.todo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.model.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun todoDao() : TodoDao

    companion object{
        @Volatile
        var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): AppDatabase{
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, "todo.db"
            ).build()
        }
    }
}