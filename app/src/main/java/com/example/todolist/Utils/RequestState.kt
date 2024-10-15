package com.example.todolist.Utils

import com.example.todolist.Model.Tasks

sealed class RequestState<out T>{
    data object Idle: RequestState<Nothing>()
    data object Loading: RequestState<Nothing>()
    data class Success<T>(val data: T): RequestState<T>()
    data class Error(val error: Throwable): RequestState<Nothing>()
}