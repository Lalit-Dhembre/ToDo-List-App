package com.example.todolist.ui.screens.TaskScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.Model.Priority
import com.example.todolist.ui.Components.PriorityDropDown
import com.example.todolist.ui.Viewmodel.SharedViewmodel

@Composable
fun TaskContent(
    modifier: Modifier,
    title: String,
    padding: PaddingValues,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPriorityChange: (Priority) -> Unit,
    sharedViewmodel: SharedViewmodel
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = {onTitleChange(it)},
            modifier = Modifier
                .fillMaxWidth(0.95f),
            label = { Text(text = "Title")})
        Spacer(modifier = Modifier.height(5.dp))
        PriorityDropDown(
            priority = sharedViewmodel.priority.value,
            onPrioritySelected = {it -> sharedViewmodel.priority.value = it})
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = description,
            onValueChange = {onDescriptionChange(it)},
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.95f),
            label = { Text(text = "Description")})
    }

}

