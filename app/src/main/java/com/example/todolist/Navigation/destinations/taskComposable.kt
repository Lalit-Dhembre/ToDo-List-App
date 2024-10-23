package com.example.todolist.Navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todolist.Utils.Actions
import com.example.todolist.Utils.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Actions) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf( navArgument(
            name = "taskId"
        ){
            type = NavType.IntType
        })
    ){

    }
}