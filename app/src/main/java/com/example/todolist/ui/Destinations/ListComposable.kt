package com.example.todolist.ui.Destinations

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todolist.Utils.Constants.LIST_ARGUMENT_KEY
import com.example.todolist.Utils.Constants.LIST_SCREEN
import com.example.todolist.Utils.toAction
import com.example.todolist.ui.Viewmodel.SharedViewmodel
import com.example.todolist.ui.screens.ListScreen.ListScreen

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
    ){ navBackStackEntry->
        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()
        Log.d("LIST COMPOSABLE",action.name)
        LaunchedEffect(key1 = action) {
            sharedViewmodel.handleDatabaseActions(action)
        }
        ListScreen(
            navigateToTaskScreen,
            sharedViewmodel = sharedViewmodel)
    }
}