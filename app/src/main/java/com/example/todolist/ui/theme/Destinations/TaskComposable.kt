package com.example.todolist.ui.theme.Destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todolist.Navigation.Action

fun NavGraphBuilder.taskComposable(
    navigateToListScreen : (Action) -> Unit
){
    composable(
        route = "TASK_SCREEN",
        arguments = listOf(
            navArgument(name = "taskId"){
                type = NavType.IntType
            }
        )
    ){

    }
}