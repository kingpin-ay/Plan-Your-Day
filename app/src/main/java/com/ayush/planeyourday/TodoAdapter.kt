package com.ayush.planeyourday



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.ayush.planeyourday.TodoAdapter.TodoViewHolder

import kotlinx.android.synthetic.main.todo_list_recycler_view_item.view.*


class TodoAdapter(
    private var todos: List<Todo>
) : RecyclerView.Adapter<TodoViewHolder>(){
    inner class TodoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_list_recycler_view_item,parent,false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.itemView.apply {
            presentLocation.text = todos[position].presentLocation
            destinationLocation.text = todos[position].destinationLocation
            dateData.text = todos[position].dateTime

        }

    }

    override fun getItemCount(): Int {
        return todos.size
    }

}


