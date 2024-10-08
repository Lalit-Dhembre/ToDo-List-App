package com.example.todolist.ui.Components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

//FLOATING ACTION BUTTON
@Composable
fun floatingAction(
    navigateToTaskScreen: (Int) -> Unit
){
    FloatingActionButton(
        onClick = { navigateToTaskScreen(-1) },
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.secondary) {
        Icon(imageVector = Icons.Filled.Add,
            contentDescription = null)
    }
}