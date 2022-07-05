package com.example.todo.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todo.db.AppDatabase
import com.example.todo.model.Todo
import com.example.todo.repositories.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    val getAllData: LiveData<List<Todo>>
    private val repository: TodoRepository


    init {
        val todoDao = AppDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        getAllData = repository.getAllData
    }

    fun addTodo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO){
            repository.addTodo(todo)
        }
    }

    fun updateTodo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteTodo(todo)
        }
    }
}