package com.example.todolist.ui.Destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todolist.Utils.Constants.LIST_ARGUMENT_KEY
import com.example.todolist.Utils.Constants.LIST_SCREEN
import com.example.todolist.ui.Viewmodel.SharedViewmodel
import com.example.todolist.ui.screens.ListScreen

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (Int) -> Unit,
    sharedViewmodel: SharedViewmodel
){
    composable(
        route = LIST_SCREEN,
        arguments = listOf(
            navArgument(name = LIST_ARGUMENT_KEY){
                type = NavType.StringType
            }
        )
    ){
        ListScreen(
            navigateToTaskScreen,
            sharedViewmodel = sharedViewmodel)
    }
}