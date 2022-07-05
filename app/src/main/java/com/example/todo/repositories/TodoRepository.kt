package com.example.todo.repositories

import androidx.lifecycle.LiveData
import com.example.todo.model.Todo
import com.example.todo.db.TodoDao

class TodoRepository(private val todoDao: TodoDao) {

    val getAllData: LiveData<List<Todo>> = todoDao.getAll()

    suspend fun addTodo(todo: Todo){
        todoDao.addTodo(todo)
    }

    suspend fun updateTodo(todo: Todo){
        todoDao.updateTodo(todo)
    }

    suspend fun deleteTodo(todo: Todo){
        todoDao.deleteTodo(todo)
    }
}