package com.example.todolist.ui.Components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun DeleteAllAction(
    onDeleteClicked : () -> Unit
){
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = { expanded = !expanded }) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "Delete All",
            tint = MaterialTheme.colorScheme.onPrimary)
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                text = { Text(text = "Delete All") },
                onClick = { onDeleteClicked() })
        }
    }
}