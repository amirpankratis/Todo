package com.example.todo.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.model.Todo
import com.example.todo.viewModels.TodoViewModel

class AddTodo : Fragment() {

    private lateinit var input: EditText
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_todo, container, false)

        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        val addBtn: Button = view.findViewById(R.id.newTodoAddBtn)
        input = view.findViewById(R.id.todoInput)

        // event on click
        addBtn.setOnClickListener{
            if(input.text.toString().isNotEmpty()){
                addTodoData(requireContext())
            }else {
                Toast.makeText(requireContext(), "the input cant be empathy", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun addTodoData(context: Context){
        val todo = Todo(0, input.text.toString(), false)
        todoViewModel.addTodo(todo)
        Toast.makeText(context, "Success Added", Toast.LENGTH_SHORT).show()
        input.text = null
        findNavController().navigate(R.id.action_addTodo_to_listOfTodos)
    }

}