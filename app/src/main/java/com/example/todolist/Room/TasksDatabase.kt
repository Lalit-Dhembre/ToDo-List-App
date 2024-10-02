package com.example.todolist.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.Model.Tasks

@Database(entities = [Tasks::class], version = 1, exportSchema = false)
abstract class TasksDatabase (): RoomDatabase(){
    abstract fun getDao() : Dao
}