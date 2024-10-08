package com.example.todolist.ui.Destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todolist.Navigation.Action
import com.example.todolist.Utils.Constants
import com.example.todolist.Utils.Constants.TASK_ARGUMENT_KEY

fun NavGraphBuilder.taskComposable(
    navigateToListScreen : (Action) -> Unit
){
    composable(
        route = Constants.TASK_SCREEN,
        arguments = listOf(
            navArgument(name = TASK_ARGUMENT_KEY){
                type = NavType.IntType
            }
        )
    ){

    }
}