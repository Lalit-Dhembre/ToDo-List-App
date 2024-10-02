package com.example.todolist.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todolist.Model.Tasks
import com.example.todolist.Utils.Constants.DATABASE_TABLE

@Dao
interface Dao {

    @Insert
    suspend fun addTask(Task : Tasks) : List<Tasks>

    @Update
    suspend fun updateTask(Task: Tasks) : List<Tasks>

    @Delete
    suspend fun deleteTask(Task: Tasks) : List<Tasks>

    @Query("Delete from ${DATABASE_TABLE}")
    suspend fun deleteAll()

    @Query("SELECT * FROM ${DATABASE_TABLE}")
    suspend fun getAllTasks() : List<Tasks>

    @Query("SELECT * FROM ${DATABASE_TABLE} ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    suspend fun sortHighToLow() : List<Tasks>

    @Query("SELECT * FROM ${DATABASE_TABLE} ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    suspend fun sortLowToHigh() : List<Tasks>

    @Query("SELECT * FROM ${DATABASE_TABLE} WHERE id = :taskId")
    suspend fun getTask(taskId: Int): List<Tasks>
}