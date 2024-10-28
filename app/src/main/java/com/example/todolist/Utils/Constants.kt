package com.example.todolist.Utils

import com.example.todolist.Navigation.Action

object Constants {
const val DATABASE_NAME = "Tasks_DB"
const val DATABASE_TABLE = "Tasks_Table"

    const val LIST_SCREEN = "list/{action}"
    const val TASK_SCREEN = "task/{taskId}"

    const val DATASTORE_PREF = "to_pref"
    const val DATASTORE_KEY = "to_key"

    const val LIST_ARGUMENT_KEY = "action"
    const val TASK_ARGUMENT_KEY = "taskId"}

fun String?.toAction():Action{
    return when{
        this == "ADD" -> {
            Action.ADD
        }
        this == "UPDATE" ->{
            Action.UPDATE
        }
        this == "DELETE" ->{
            Action.DELETE
        }
        this == "UNDO" ->{
            Action.UNDO
        }
        this == "DELETE_ALL" ->{
            Action.DELETE_ALL
        }
        else -> {
            Action.NO_ACTION
        }
    }
}