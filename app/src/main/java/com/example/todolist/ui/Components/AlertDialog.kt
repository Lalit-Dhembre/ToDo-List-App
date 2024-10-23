package com.example.todolist.ui.Components

import android.app.AlertDialog
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AlertDialog(
    title: String,
    message: String,
    openDialog: Boolean,
    closeDialog: () -> Unit,
    onYesClicked: () -> Unit
){
    if(openDialog){
        AlertDialog(
            title = {
                Text(text = title,
                    style = MaterialTheme.typography.headlineMedium)
            },
            text = {
                Text(text = message,
                    style = MaterialTheme.typography.bodySmall)
            },
            confirmButton = {
                Button(onClick = {
                    onYesClicked()
                    closeDialog()}) {
                    Text(text = "Yes")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { closeDialog() }) {
                    Text(text = "No")
                }
            },
            onDismissRequest = { closeDialog() })
    }
}