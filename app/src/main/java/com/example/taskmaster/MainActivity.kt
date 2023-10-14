package com.example.taskmaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var taskRepository: TaskRepository
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = TaskDbHelper(this)
        taskRepository = TaskRepository(dbHelper)

        val tasks = taskRepository.getAllTasks()
        taskAdapter = TaskAdapter(tasks)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewTasks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = taskAdapter
        recyclerView.setHasFixedSize(true)
    }

    fun addTask(view: View){
        val ediTextTask : EditText = findViewById(R.id.editText)
        val taskDesciption = ediTextTask.text.toString()

        if(taskDesciption.isNotEmpty()){
            val initialSize = taskAdapter.itemCount
            taskRepository.addTask(taskDesciption)
            val updatedTasks = taskRepository.getAllTasks()
            taskAdapter.clearTasks()
            taskAdapter.updateTasks(updatedTasks)
            val finalSize = taskAdapter.itemCount

            Log.d("AddTask", "InitialSize: $initialSize, FinalSize: $finalSize" )
            taskAdapter.notifyDataSetChanged()
            ediTextTask.text.clear()
        }

    }

//    fun updateTasks(view: View){
//
//    }
}