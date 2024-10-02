package com.example.todolist.Model

import com.example.todolist.Room.Dao

class Repository(private val dao: Dao) {
    suspend fun getTasks(): List<Tasks> = dao.getAllTasks()
    suspend fun sortAsc(): List<Tasks> = dao.sortLowToHigh()
    suspend fun sortDes(): List<Tasks> = dao.sortHighToLow()

    suspend fun addTask(task: Tasks):List<Tasks> = dao.addTask(task)
    suspend fun deleteTask(task: Tasks):List<Tasks> = dao.deleteTask(task)
    suspend fun updateTask(task: Tasks):List<Tasks> = dao.updateTask(task)

    suspend fun getTask(taskId: Int):List<Tasks> = dao.getTask(taskId)
    suspend fun deleteAll() = dao.deleteAll()
}