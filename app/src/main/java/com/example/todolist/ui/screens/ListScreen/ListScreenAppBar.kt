package com.example.todolist.ui.screens.ListScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import com.example.todolist.Model.Priority
import com.example.todolist.ui.Components.DeleteAllAction
import com.example.todolist.ui.Components.SearchAction
import com.example.todolist.ui.Components.SortAction

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