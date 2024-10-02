package com.example.todolist.Model

import androidx.compose.ui.graphics.Color
import com.example.todolist.ui.theme.HighPriority
import com.example.todolist.ui.theme.LowPriority
import com.example.todolist.ui.theme.MediumPriority
import com.example.todolist.ui.theme.NoPriority

enum class Priority(val color: Color) {
    LOW(LowPriority),
    MEDIUM(MediumPriority),
    HIGH(HighPriority),
    NONE(NoPriority)
}