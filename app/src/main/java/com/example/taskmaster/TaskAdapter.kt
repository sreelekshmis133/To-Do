package com.example.taskmaster

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.logging.Handler

class TaskAdapter(private val tasks: MutableList<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : TaskViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = tasks[position]
        holder.checkBoxCompleted.isChecked = currentTask.isCompleted
        holder.textViewDecription.text = currentTask.description
        Log.d("RecyclerView", "Position: $position")

        if(currentTask.isCompleted) {
            holder.textViewDecription.paintFlags = holder.textViewDecription.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.textViewDecription.paintFlags = holder.textViewDecription.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        holder.checkBoxCompleted.setOnCheckedChangeListener{ _, isChecked ->
            val currentTask = tasks[position]
            currentTask.isCompleted = isChecked
            android.os.Handler().post {
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun clearTasks(){
        tasks.clear()
    }
    fun updateTasks(newTasks: List<Task>) {
//        tasks.clear()
        tasks.addAll(newTasks)
        notifyDataSetChanged()
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewDecription : TextView = itemView.findViewById(R.id.textViewDescription)
        val checkBoxCompleted : CheckBox = itemView.findViewById(R.id.checkBoxCompleted)
    }
}
