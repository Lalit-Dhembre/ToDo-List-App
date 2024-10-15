package com.example.todolist.Navigation

import androidx.navigation.NavHostController
import com.example.todolist.Utils.Constants.LIST_SCREEN
import com.example.todolist.ui.Viewmodel.SharedViewmodel


class Screens(
    val navHostController: NavHostController) {
    val list: (Action) -> Unit = {action ->
        navHostController.navigate(route = "list/${action.name}"){
            popUpTo("list/NO_ACTION"){inclusive = true}
        }
    }

    val task: (Int) -> Unit = {taskId ->
        navHostController.navigate(route = "task/$taskId")
    }
}