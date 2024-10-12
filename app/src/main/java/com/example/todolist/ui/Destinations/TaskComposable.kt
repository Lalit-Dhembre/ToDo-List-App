package com.example.todolist.ui.Destinations

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todolist.Navigation.Action
import com.example.todolist.Utils.Constants
import com.example.todolist.Utils.Constants.TASK_ARGUMENT_KEY
import com.example.todolist.ui.Viewmodel.SharedViewmodel
import com.example.todolist.ui.screens.TaskScreen.TaskScreen

fun NavGraphBuilder.taskComposable(
    navigateToListScreen : (Action) -> Unit,
    sharedViewmodel: SharedViewmodel
){
    composable(
        route = Constants.TASK_SCREEN,
        arguments = listOf(
            navArgument(name = TASK_ARGUMENT_KEY){
                type = NavType.IntType
            }
        )
    ){navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(Constants.TASK_ARGUMENT_KEY)
        sharedViewmodel.getSelectedTask(taskId)
        val selectedTask by sharedViewmodel.selectedTasks.collectAsState()
        LaunchedEffect(key1 = selectedTask) {
            Log.d("debug2",taskId.toString())
            sharedViewmodel.updateFields(selectedTask)
            Log.d("debug", selectedTask.toString())
        }
        TaskScreen(
            navigateToListScreen = navigateToListScreen,
            selectedTasks = selectedTask,
            sharedViewmodel = sharedViewmodel
        )
    }
}