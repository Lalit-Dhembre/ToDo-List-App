package com.example.todolist.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todolist.Model.Tasks
import com.example.todolist.Utils.Constants.DATABASE_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert
    suspend fun addTask(Task : Tasks)

    @Update
    suspend fun updateTask(Task: Tasks)

    @Delete
    suspend fun deleteTask(Task: Tasks)

    @Query("Delete from $DATABASE_TABLE")
    suspend fun deleteAll()

    @Query("SELECT * FROM $DATABASE_TABLE")
    fun getAllTasks() : Flow<List<Tasks>>

    @Query("SELECT * FROM $DATABASE_TABLE ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortHighToLow() : Flow<List<Tasks>>

    @Query("SELECT * FROM $DATABASE_TABLE ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun sortLowToHigh() : Flow<List<Tasks>>

    @Query("SELECT * FROM $DATABASE_TABLE WHERE id = :taskId")
    fun getTask(taskId: Int): Flow<Tasks>

    @Query("SELECT * FROM $DATABASE_TABLE WHERE title LIKE :searchQuery OR description LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<Tasks>>
}