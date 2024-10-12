package com.example.todolist.ui.screens.TaskScreen

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.todolist.Model.Priority
import com.example.todolist.Model.Tasks
import com.example.todolist.Navigation.Action
import com.example.todolist.ui.Components.TaskScreenAppBar
import com.example.todolist.ui.Viewmodel.SharedViewmodel

@Composable
fun TaskScreen(
    navigateToListScreen: (Action) -> Unit,
    selectedTasks: Tasks?,
    sharedViewmodel: SharedViewmodel
){
    val title:String by sharedViewmodel.title
    val description: String by sharedViewmodel.description
    val priority: Priority by sharedViewmodel.priority
    Scaffold(
        topBar = {
            TaskScreenAppBar(
                navigateToListScreen = navigateToListScreen,
                selectedTasks = selectedTasks)
        },
        content = {padding->
            TaskContent(
                modifier = Modifier.consumeWindowInsets(padding),
                padding = padding,
                title = title,
                onTitleChange = {
                    sharedViewmodel.title.value = it
                },
                description = description,
                onDescriptionChange = {
                    sharedViewmodel.description.value = it
                },
                priority = priority,
                onPriorityChange = {
                    sharedViewmodel.priority.value = it
                },
                sharedViewmodel = sharedViewmodel
            )
        },

    )
}