package com.example.todolist.ui.Components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolist.Model.Priority
import com.example.todolist.Model.Tasks
import com.example.todolist.Navigation.Action

@Composable
fun TaskScreenAppBar(
    navigateToListScreen: (Action) -> Unit,
    selectedTasks: Tasks?
) {
    if(selectedTasks == null){
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    }
    else{
        UpdateTaskBar(
            navigateToListScreen = navigateToListScreen,
            selectedTasks = selectedTasks)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskAppBar(
    navigateToListScreen: (Action) -> Unit
){
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {navigateToListScreen(Action.NO_ACTION) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Button",
                    tint = MaterialTheme.colorScheme.inversePrimary
                )
            }
        },
        title = {
            Text(
                text = "New Task",
                color = MaterialTheme.colorScheme.onPrimary)
        },
        actions = {
            AddAction(addActionClicked = navigateToListScreen)
        },
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.primary
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTaskBar(
    navigateToListScreen: (Action) -> Unit,
    selectedTasks: Tasks
){
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {navigateToListScreen(Action.NO_ACTION) }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Back Button",
                    tint = MaterialTheme.colorScheme.inversePrimary
                )
            }
        },
        title = {
            Text(
                text = selectedTasks.title,
                color = MaterialTheme.colorScheme.onPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)
        },
        actions = {
            DeleteAction(deleteActionClicked = navigateToListScreen)
            UpdateAction(updateActionClicked = navigateToListScreen)
        },
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.primary
        )
    )
}



@Composable
fun AddAction(
    addActionClicked : (Action) -> Unit
){
    IconButton(onClick = { addActionClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = "Add Tasks",
            tint = MaterialTheme.colorScheme.onPrimary)
    }
}

@Composable
fun DeleteAction(
    deleteActionClicked : (Action) -> Unit
){
    IconButton(onClick = { deleteActionClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Delete Tasks",
            tint = MaterialTheme.colorScheme.onPrimary)
    }
}

@Composable
fun UpdateAction(
    updateActionClicked : (Action) -> Unit
){
    IconButton(onClick = { updateActionClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Filled.CheckCircle,
            contentDescription = "Delete Tasks",
            tint = MaterialTheme.colorScheme.onPrimary)
    }
}


@Composable
@Preview
fun AddTaskBarPreview(){
    NewTaskAppBar(
        navigateToListScreen = {}
    )
}

@Composable
@Preview
fun ExistingTaskbarPreview(){
    UpdateTaskBar(navigateToListScreen = {}, selectedTasks =
    Tasks(
        id = 1,
        title = "CODING",
        description = "MAKE AN APP",
        priority = Priority.HIGH
    )
    )
}