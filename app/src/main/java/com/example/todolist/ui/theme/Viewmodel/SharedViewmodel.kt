package com.example.todolist.ui.theme.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.Model.Repository
import com.example.todolist.Model.Tasks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewmodel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _allTasks = MutableStateFlow<List<Tasks>>(emptyList())

    val allTasks = _allTasks

    fun getAllTasks(){
        viewModelScope.launch {
            val tasks = repository.getTasks()
            _allTasks.value = tasks
        }
    }
}