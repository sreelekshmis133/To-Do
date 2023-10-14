package com.example.taskmaster

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TaskDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        const val DATABASE_NAME = "tasks.db"
        const val DATABASE_VERSION = 1;
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(TaskContract.SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(TaskContract.SQL_DELETE_ENTRIES)
        onCreate(db)
    }

}