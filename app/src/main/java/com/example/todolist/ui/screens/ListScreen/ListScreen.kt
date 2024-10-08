package com.example.todolist.ui.screens.ListScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import com.example.todolist.R
import com.example.todolist.Utils.SearchAppBarStates
import com.example.todolist.ui.Components.DeleteAllAction
import com.example.todolist.ui.Components.PriorityItem
import com.example.todolist.ui.Components.floatingAction
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
        content = {
            ListContent()
        }

    )
}
