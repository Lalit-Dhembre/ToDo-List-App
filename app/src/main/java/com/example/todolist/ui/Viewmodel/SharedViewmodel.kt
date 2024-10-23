package com.example.todolist.ui.Viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.Model.Priority
import com.example.todolist.Model.Repository
import com.example.todolist.Model.Tasks
import com.example.todolist.Navigation.Action
import com.example.todolist.Utils.RequestState
import com.example.todolist.Utils.SearchAppBarStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewmodel @Inject constructor(
    private val repository: Repository
): ViewModel() {

   public val searchAppBarStates = mutableStateOf(SearchAppBarStates.CLOSED)
    val searchTextState = mutableStateOf("")

    private val _allTasks = MutableStateFlow<RequestState<List<Tasks>>>(RequestState.Idle)

    val allTasks:StateFlow<RequestState<List<Tasks>>> = _allTasks

    val action = mutableStateOf(Action.NO_ACTION)

    fun getAllTasks(){
        _allTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.getAllTasks.collect{
                    _allTasks.value = RequestState.Success(it)
                }
            }
        }catch (err: Exception){
            _allTasks.value = RequestState.Error(err)
        }
    }


    private val _selectedTask: MutableStateFlow<Tasks?> = MutableStateFlow(null)
    val selectedTasks:StateFlow<Tasks?> = _selectedTask

    fun getSelectedTask(taskId:Int){
        viewModelScope.launch {
            repository.getSelectedTask(taskId).collect() { task ->
                _selectedTask.value = task
                Log.d("debug1",taskId.toString())
            }
        }
    }
    val id:MutableState<Int> = mutableIntStateOf(0)
    val title:MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.LOW)

    fun updateFields(task:Tasks?){
            if(task != null){
                id.value = task.id
                title.value = task.title
                description.value = task.description
                priority.value = task.priority
            }
            else{
                id.value = 0
                title.value = ""
                description.value = ""
                priority.value = Priority.LOW
            }
        }

    fun maxLimit(text:String){
        if(text.length < 20){
            title.value = text
        }
    }

    fun validateFields():Boolean{
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }

    fun addTask(){
        viewModelScope.launch(Dispatchers.IO) {
            val task = Tasks(
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            repository.addTask(task)
        }
    }

    fun updateTask(){
        viewModelScope.launch(Dispatchers.IO) {
            val task = Tasks(
                id = id.value,
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            repository.updateTask(task)
        }
    }
    fun deleteTask(){
        viewModelScope.launch(Dispatchers.IO) {
            val task = Tasks(
                id = id.value,
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            repository.deleteTask(task)
        }
    }

    fun deleteAllTasks(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTasks()
        }
    }
    fun handleDatabaseActions(action: Action){
        when(action){
            Action.ADD ->{
                addTask()
            }
            Action.UPDATE ->{
                updateTask()
            }
            Action.DELETE ->{
                deleteTask()
            }
            Action.DELETE_ALL ->{
                deleteAllTasks()
            }
            Action.UNDO ->{

            }
            else ->{
                Action.NO_ACTION
            }
        }
    }

    }
