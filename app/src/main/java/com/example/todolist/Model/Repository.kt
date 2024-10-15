package com.example.todolist.Model

import com.example.todolist.Room.Dao
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class Repository @Inject constructor(private val dao: Dao) {
    val getAllTasks: Flow<List<Tasks>> = dao.getAllTasks()
    val sortByLowPriority: Flow<List<Tasks>> = dao.sortLowToHigh()
    val sortByHighPriority: Flow<List<Tasks>> = dao.sortHighToLow()
    fun getSelectedTask(taskId: Int): Flow<Tasks> {
        return dao.getTask(taskId = taskId)
    }

    suspend fun addTask(task: Tasks) {
        dao.addTask(Task = task)
    }

    suspend fun updateTask(task: Tasks) {
        dao.updateTask(Task = task)
    }

    suspend fun deleteTask(task: Tasks) {
        dao.deleteTask(Task = task)
    }

    suspend fun deleteAllTasks() {
        dao.deleteAll()
    }

    fun searchDatabase(searchQuery: String): Flow<List<Tasks>> {
        return dao.searchDatabase(searchQuery = searchQuery)
    }
}