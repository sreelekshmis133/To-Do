package com.example.taskmaster

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import com.example.taskmaster.TaskContract.TaskEntry

class TaskRepository(private val dbHelper: TaskDbHelper) {
    fun addTask(description: String){
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(TaskEntry.COLUMN_DESCRIPTION, description)
            put(TaskEntry.COLUMN_COMPLETED, 0)
        }

        db.insert(TaskEntry.TABLE_NAME, null, values)
    }

    fun getAllTasks() : MutableList<Task> {
        val db = dbHelper.readableDatabase
        val projection = arrayOf(BaseColumns._ID, TaskEntry.COLUMN_DESCRIPTION, TaskEntry. COLUMN_COMPLETED)

        val cursor: Cursor = db.query(
            TaskEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        val tasks = mutableListOf<Task>()

        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndexOrThrow(BaseColumns._ID))
            val description = cursor.getString(cursor.getColumnIndexOrThrow(TaskEntry.COLUMN_DESCRIPTION))
            val isCompleted = cursor.getInt(cursor.getColumnIndexOrThrow(TaskEntry.COLUMN_COMPLETED)) != 0

            tasks.add(Task(id, description, isCompleted))
        }

        cursor.close()
        return tasks
    }
}