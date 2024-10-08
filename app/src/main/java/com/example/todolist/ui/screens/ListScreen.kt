package com.example.todolist.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolist.Model.Priority
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.todolist.R
import com.example.todolist.Utils.SearchAppBarStates
import com.example.todolist.ui.Viewmodel.SharedViewmodel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToTaskScreen: (Int) -> Unit,
    sharedViewmodel: SharedViewmodel
){
    Scaffold(
        topBar = {
            val searchAppBarState : SearchAppBarStates by sharedViewmodel.searchAppBarStates
            when(searchAppBarState){
              SearchAppBarStates.CLOSED -> {
                  DefaultAppBar(
                onSearchClicked = {sharedViewmodel.searchAppBarStates.value = SearchAppBarStates.OPENED },
                onSortClicked = {},
                onDeleteClicked = {}
            )
              }
                SearchAppBarStates.OPENED -> {
                    SearchTopBar(
                        text = sharedViewmodel.searchTextState.value,
                        onTextChange = { sharedViewmodel.searchTextState.value = it},
                        onCloseClicked = {
                            if(sharedViewmodel.searchTextState.value.isNotBlank()){
                                sharedViewmodel.searchTextState.value = ""
                            }
                            else{
                                sharedViewmodel.searchAppBarStates.value = SearchAppBarStates.CLOSED
                                sharedViewmodel.searchTextState.value = ""
                            }
                            },
                        onSearchClicked = {})

                }
                SearchAppBarStates.TRIGGERED -> {}
                }
            },
        floatingActionButton = { floatingAction(navigateToTaskScreen) },

    ) {

    }
}

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

// APP BAR AND ACTIONS
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultAppBar(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
){
    TopAppBar(
        title = { Text(text = "Tasks") },
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.primary
        ),
        actions = {
            SearchAction(onSearchClicked = onSearchClicked)
            SortAction(onSortClicked = onSortClicked)
            DeleteAllAction(onDeleteClicked = onDeleteClicked)
            }
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    text: String,
    onTextChange : (String) -> Unit,
    onCloseClicked : () -> Unit,
    onSearchClicked : (String) -> Unit
){
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding(),
            value = text,
            onValueChange = {onTextChange(it)},
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(DefaultAlpha),
                    text = "Search",
                    color = Color.White)
            },
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.onPrimary)
                }
            },
            trailingIcon = {
                IconButton(onClick = { onCloseClicked() }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close Search",
                        tint = MaterialTheme.colorScheme.onPrimary)
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {onSearchClicked(text)}
            ),

        )

    }

}

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

@Composable
fun SortAction(
    onSortClicked : (Priority) -> Unit
){
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(
        onClick = { expanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_filter_list_24),
            contentDescription = "FILTER LIST",
            tint = MaterialTheme.colorScheme.onPrimary)
    DropdownMenu(expanded = expanded,
        onDismissRequest = { expanded = false }) {
        DropdownMenuItem(
            text = { PriorityItem(priority = Priority.LOW) },
            onClick = {
                expanded = false
                onSortClicked(Priority.LOW)
            })
        DropdownMenuItem(
            text = { PriorityItem(priority = Priority.MEDIUM) },
            onClick = {
                expanded = false
                onSortClicked(Priority.MEDIUM)
            })
        DropdownMenuItem(
            text = { PriorityItem(priority = Priority.HIGH) },
            onClick = {
                expanded = false
                onSortClicked(Priority.HIGH)
            })
        DropdownMenuItem(
            text = { PriorityItem(priority = Priority.NONE) },
            onClick = {
                expanded = false
                onSortClicked(Priority.NONE)
            })
    }
    }
}

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



//Items
@Composable
fun PriorityItem(
    priority: Priority
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Canvas(modifier = Modifier.size(16.dp), contentDescription = "Priority") {
            drawCircle(priority.color)
        }
        Text(text = priority.name,
            modifier = Modifier.padding(start = 16.dp),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface)
    }
}


//PREVIEWS

@Preview
@Composable
fun DefaultAppBarPreview(){
    DefaultAppBar(
        onSearchClicked = {},
        onSortClicked = {},
        onDeleteClicked = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PriorityItemPreview(){
    PriorityItem(priority = Priority.LOW)
}

@Preview
@Composable
fun SearchBarPreview(){
    SearchTopBar(
        text = "",
        onTextChange = {},
        onCloseClicked = { /*TODO*/ }) {

    }
}

