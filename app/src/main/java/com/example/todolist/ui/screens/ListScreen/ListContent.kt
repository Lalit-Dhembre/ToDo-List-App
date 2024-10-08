package com.example.todolist.ui.screens.ListScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.example.todolist.Model.Priority
import com.example.todolist.Model.Tasks

@Composable
fun ListContent(){

}

@Composable
fun TaskItem(
    task:Tasks,
    navigateToTaskScreen : (taskId: Int) -> Unit
){
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = RectangleShape,
        shadowElevation = 12.dp,
        onClick = {
            navigateToTaskScreen(task.id)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    modifier = Modifier
                        .weight(9f),
                    text = task.title,
                    style = MaterialTheme.typography.headlineMedium,
                )
                Canvas(
                    modifier = Modifier
                        .weight(1f),
                    contentDescription = "") {
                    drawCircle(
                        color = task.priority.color,
                        radius = 20f)
                }
            }
            Text(
                style = MaterialTheme.typography.bodySmall,
                text = task.description,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis)
        }
    }
}

@Preview
@Composable
fun TaskItemPreview(){
    var sampleTask : Tasks = Tasks(
        id = 1,
        title = "Task 1",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
        priority = Priority.HIGH
    )

    TaskItem(task = sampleTask){}
}