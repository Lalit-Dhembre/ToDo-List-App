package com.example.todolist.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todolist.Utils.Constants.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
data class Tasks(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    val title : String,
    val description : String,
    val priority: Priority
)