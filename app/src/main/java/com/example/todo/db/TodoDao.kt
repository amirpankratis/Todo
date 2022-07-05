package com.example.todo.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todo.model.Todo

@Dao
interface TodoDao {

    @Query("SELECT * FROM todos")
    fun getAll(): LiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)
}