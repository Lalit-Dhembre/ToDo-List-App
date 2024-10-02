package com.example.todolist.Navigation

import androidx.navigation.NavHostController
import com.example.todolist.Utils.Constants.LIST_SCREEN


class Screens(val navHostController: NavHostController) {
    val list: (Action) -> Unit = {action ->
        navHostController.navigate(route = "list/${action.name}"){
            popUpTo(LIST_SCREEN){inclusive = true}
        }
    }

    val task: (Int) -> Unit = {taskId ->
        navHostController.navigate(route = "task/$taskId")
    }
}