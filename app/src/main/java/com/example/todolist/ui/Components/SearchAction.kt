package com.example.todolist.ui.Components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit
){
    IconButton(onClick = { onSearchClicked() }) {
        Icon(
            imageVector = Icons.Filled.Search,
            tint = MaterialTheme.colorScheme.onPrimary,
            contentDescription = "Search Action")
    }
}