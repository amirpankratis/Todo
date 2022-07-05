package com.example.todo.adapters

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.model.Todo

class TodoAdapter(
    private val todos: MutableList<Todo>,
    private val todoClickDeleteInterface: TodoClickDeleteInterface,
    private val todoClickCheckInterface: TodoClickCheckInterface
    ): RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.eachTodoText)
        val check: CheckBox = view.findViewById(R.id.eachTodoCheck)
        val btn: Button = view.findViewById(R.id.eachTodoBtn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.each_todo,
            parent,
            false
        )
            return ViewHolder(view)
    }

    private fun makeTextLineOne(text: TextView, isChecked: Boolean){
        if(isChecked){
            text.paintFlags = text.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else {
            text.paintFlags = text.paintFlags or STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = todos[position]
        val text = holder.text
        val check = holder.check
        holder.btn

        text.text = item.title
        check.isChecked = item.done

        makeTextLineOne(holder.text, item.done)

        holder.btn.setOnClickListener{
            todoClickDeleteInterface.onTodoDeleteBtn(item)
        }

        holder.check.setOnCheckedChangeListener{_, isChecked ->
            makeTextLineOne(holder.text, isChecked)
            todoClickCheckInterface.onTodoCheckBox(item, isChecked)
        }



    }

    override fun getItemCount(): Int {
        return todos.size
    }

}

interface TodoClickDeleteInterface{
    fun onTodoDeleteBtn(todo: Todo)
}

interface TodoClickCheckInterface{
    fun onTodoCheckBox(todo: Todo, isChecked: Boolean)
}