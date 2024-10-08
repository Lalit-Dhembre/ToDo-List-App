package com.example.todolist.ui.Viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.Model.Repository
import com.example.todolist.Model.Tasks
import com.example.todolist.Utils.SearchAppBarStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewmodel @Inject constructor(
    private val repository: Repository
): ViewModel() {

   public val searchAppBarStates = mutableStateOf(SearchAppBarStates.CLOSED)
    val searchTextState = mutableStateOf("")

    private val _allTasks = MutableStateFlow<List<Tasks>>(emptyList())

    val allTasks = _allTasks

    fun getAllTasks(){
        viewModelScope.launch {
            val tasks = repository.getTasks()
            _allTasks.value = tasks
        }
    }
}