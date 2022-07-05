package com.example.todo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.adapters.TodoAdapter
import com.example.todo.adapters.TodoClickCheckInterface
import com.example.todo.adapters.TodoClickDeleteInterface
import com.example.todo.model.Todo
import com.example.todo.viewModels.TodoViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListOfTodos : Fragment(), TodoClickDeleteInterface, TodoClickCheckInterface {

    private lateinit var todoViewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list_of_todos, container, false)

        //get inputs
        val addBtn = view.findViewById<FloatingActionButton>(R.id.addTodoBtn)

        addBtn.setOnClickListener{
            findNavController().navigate(R.id.action_listOfTodos_to_addTodo)
        }

        //TodoViewModel
        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        // get RecycleView and init
        val rv = view.findViewById<RecyclerView>(R.id.rvTodos)
        rv.layoutManager = LinearLayoutManager(requireContext())

        // get data
        todoViewModel.getAllData.observe(viewLifecycleOwner , Observer {
            val list = it.toMutableList()
            rv.adapter = TodoAdapter(list, this,this)
        })

        return view
    }

    override fun onTodoDeleteBtn(todo: Todo) {
        todoViewModel.deleteTodo(todo)
        Toast.makeText(requireContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show()
    }

    override fun onTodoCheckBox(todo: Todo, isChecked: Boolean) {
        val newTodo = Todo(todo.id, todo.title, isChecked)
        todoViewModel.updateTodo(newTodo)
        if(isChecked){
            Toast.makeText(requireContext(), "Marked As Done", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(), "Marked As Undone", Toast.LENGTH_SHORT).show()
        }

    }

}