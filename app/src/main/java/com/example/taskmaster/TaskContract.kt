package com.example.taskmaster

import android.provider.BaseColumns

object TaskContract {

    object TaskEntry : BaseColumns{
        val TABLE_NAME = "tasks"
        val COLUMN_DESCRIPTION = "description"
        val COLUMN_COMPLETED = "completed"
    }

    val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${TaskEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${TaskEntry.COLUMN_DESCRIPTION} TEXT," +
                "${TaskEntry.COLUMN_COMPLETED} INTEGER )"

    val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${TaskEntry.TABLE_NAME}"
}