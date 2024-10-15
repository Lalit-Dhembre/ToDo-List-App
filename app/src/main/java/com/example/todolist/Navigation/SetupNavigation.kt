package com.example.todolist.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.todolist.Utils.Constants
import com.example.todolist.Utils.Constants.LIST_SCREEN
import com.example.todolist.ui.Destinations.listComposable
import com.example.todolist.ui.Destinations.taskComposable
import com.example.todolist.ui.Viewmodel.SharedViewmodel

@Composable
fun SetUpnavigation(
    navHostController: NavHostController,
    sharedViewmodel: SharedViewmodel
){
    val screen = remember(navHostController){
        Screens(navHostController)
    }

    NavHost(
        navController = navHostController,
        startDestination = "list/NO_ACTION") {
                listComposable(
                    navigateToTaskScreen = screen.task,
                    sharedViewmodel = sharedViewmodel
                )
                taskComposable(
                    navigateToListScreen = screen.list,
                    sharedViewmodel = sharedViewmodel
                )

    }
}