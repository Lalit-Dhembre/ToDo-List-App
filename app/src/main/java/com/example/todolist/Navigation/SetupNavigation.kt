package com.example.todolist.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.todolist.Utils.Constants.LIST_SCREEN
import com.example.todolist.ui.theme.Destinations.listComposable
import com.example.todolist.ui.theme.Destinations.taskComposable

@Composable
fun SetUpnavigation(
    navHostController: NavHostController
){
    val screen = remember(navHostController){
        Screens(navHostController)
    }

    NavHost(
        navController = navHostController,
        startDestination = LIST_SCREEN) {
                listComposable(
                    navigateToTaskScreen = screen.task
                )
                taskComposable(
                    navigateToListScreen = screen.list
                )

    }
}